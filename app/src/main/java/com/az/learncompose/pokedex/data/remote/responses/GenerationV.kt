package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class GenerationV(
    @Json(name = "black-white")
    val blackWhite: BlackWhite
)