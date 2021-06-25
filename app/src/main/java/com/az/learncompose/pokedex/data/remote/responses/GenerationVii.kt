package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class GenerationVii(
    @Json(name = "icons")
    val icons: Icons,
    @Json(name = "ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon
)