package com.az.learncompose.pokedex.ui.view.pokemondetail

import androidx.lifecycle.ViewModel
import com.az.learncompose.pokedex.data.remote.responses.Pokemon
import com.az.learncompose.pokedex.repository.pokemon.PokemonRepository
import com.az.learncompose.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{
        return pokemonRepository.getPokemonInfo(pokemonName)
    }
}