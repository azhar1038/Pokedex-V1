package com.az.learncompose.pokedex.repository.pokemon

import com.az.learncompose.pokedex.data.remote.PokeApi
import com.az.learncompose.pokedex.data.remote.responses.Pokemon
import com.az.learncompose.pokedex.data.remote.responses.PokemonList
import com.az.learncompose.pokedex.util.Resource
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try{
            pokeApi.getPokemonList(limit, offset)
        }catch(e: Exception){
            return Resource.Error("Something went wrong!")
        }

        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try{
            pokeApi.getPokemonInfo(pokemonName)
        }catch(e: Exception){
            return Resource.Error("Something went wrong!")
        }

        return Resource.Success(response)
    }
}