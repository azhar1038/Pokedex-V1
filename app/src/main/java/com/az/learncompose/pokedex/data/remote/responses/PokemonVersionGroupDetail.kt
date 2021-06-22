package com.az.learncompose.pokedex.data.remote.responses

data class PokemonVersionGroupDetail(
    val levelLearnedAt: Int,
    val moveLearnMethod: PokemonMoveLearnMethod,
    val versionGroup: PokemonVersionGroup
)