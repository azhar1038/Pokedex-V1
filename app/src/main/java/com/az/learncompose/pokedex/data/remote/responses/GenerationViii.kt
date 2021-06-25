package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class GenerationViii(
    @Json(name = "icons")
    val icons: IconsX
)