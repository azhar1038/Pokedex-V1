package com.az.learncompose.pokedex.data.remote.responses

data class PokemonAbility(
    val ability: PokemonAbilityX,
    val isHidden: Boolean,
    val slot: Int
)