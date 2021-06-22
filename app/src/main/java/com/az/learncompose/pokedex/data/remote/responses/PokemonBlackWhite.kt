package com.az.learncompose.pokedex.data.remote.responses

data class PokemonBlackWhite(
    val animated: PokemonAnimated,
    val backDefault: String,
    val backFemale: String,
    val backShiny: String,
    val backShinyFemale: String,
    val frontDefault: String,
    val frontFemale: String,
    val frontShiny: String,
    val frontShinyFemale: String
)