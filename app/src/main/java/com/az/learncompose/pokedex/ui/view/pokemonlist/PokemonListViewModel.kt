package com.az.learncompose.pokedex.ui.view.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.az.learncompose.pokedex.data.models.PokedexListEntry
import com.az.learncompose.pokedex.repository.pokemon.PokemonRepository
import com.az.learncompose.pokedex.util.Constants.PAGE_SIZE
import com.az.learncompose.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var currentPage = 0
    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    val isSearching = mutableStateOf(false)

    fun searchPokemonList(query: String){
        val listToSearch = if(isSearchStarting){
            pokemonList.value
        }else{
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }

            if(isSearchStarting){
                cachedPokemonList = pokemonList.value
                isSearchStarting = false
            }

            pokemonList.value = results
            isSearching.value = true
        }

    }

    init {
        loadPokemonPaginated()
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            when(val result = pokemonRepository.getPokemonList(PAGE_SIZE, currentPage * PAGE_SIZE)){
                is Resource.Success -> {
                    endReached.value = currentPage * PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed{ index, entry ->
                        val number = if(entry.url.endsWith("/")){
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        }else{
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
                        PokedexListEntry(
                            entry.name.replaceFirstChar { it.uppercase() },
                            url,
                            number.toInt()
                        )
                    }
                    currentPage++
                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries
                }

                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }

            }

        }
    }

    suspend fun getBitmap(request: ImageRequest): Bitmap?{
        return when(val result = Coil.execute(request)){
            is SuccessResult -> result.drawable.toBitmap()
            else -> null
        }
    }

    fun calcDominantColor(
        bmp: Bitmap,
        onFinish: (Color) -> Unit
    ) {
//        val bmp = (drawable as BitmapDrawable).bitmap.copy(
//            Bitmap.Config.ARGB_8888,
//            true
//        )

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}