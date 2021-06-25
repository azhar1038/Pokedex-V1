package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class Type(
    @Json(name = "slot")
    val slot: Int,
    @Json(name = "type")
    val type: TypeX
)