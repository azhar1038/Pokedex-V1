package com.az.learncompose.pokedex.data.remote.responses

data class PokemonMove(
    val move: PokemonMoveX,
    val versionGroupDetails: List<PokemonVersionGroupDetail>
)