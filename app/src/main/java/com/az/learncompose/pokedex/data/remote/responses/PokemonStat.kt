package com.az.learncompose.pokedex.data.remote.responses

data class PokemonStat(
    val baseStat: Int,
    val effort: Int,
    val stat: PokemonStatX
)