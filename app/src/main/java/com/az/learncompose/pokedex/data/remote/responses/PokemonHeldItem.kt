package com.az.learncompose.pokedex.data.remote.responses

data class PokemonHeldItem(
    val item: PokemonItem,
    val versionDetails: List<PokemonVersionDetail>
)