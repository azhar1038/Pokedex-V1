package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class VersionGroup(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)