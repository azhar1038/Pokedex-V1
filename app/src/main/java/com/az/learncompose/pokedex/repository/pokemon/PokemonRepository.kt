package com.az.learncompose.pokedex.repository.pokemon

import com.az.learncompose.pokedex.data.remote.responses.Pokemon
import com.az.learncompose.pokedex.data.remote.responses.PokemonList
import com.az.learncompose.pokedex.util.Resource

interface PokemonRepository {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int,
    ): Resource<PokemonList>

    suspend fun getPokemonInfo(
        pokemonName: String
    ): Resource<Pokemon>
}