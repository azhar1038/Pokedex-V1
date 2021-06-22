package com.az.learncompose.pokedex.data.remote.responses

data class PokemonSprites(
    val backDefault: String,
    val backFemale: String,
    val backShiny: String,
    val backShinyFemale: String,
    val frontDefault: String,
    val frontFemale: String,
    val frontShiny: String,
    val frontShinyFemale: String,
    val other: PokemonOther,
    val versions: PokemonVersions
)