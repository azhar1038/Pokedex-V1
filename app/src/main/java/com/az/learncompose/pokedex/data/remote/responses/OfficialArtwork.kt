package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class OfficialArtwork(
    @Json(name = "front_default")
    val frontDefault: String
)