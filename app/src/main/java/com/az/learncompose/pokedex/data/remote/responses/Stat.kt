package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class Stat(
    @Json(name = "base_stat")
    val baseStat: Int,
    @Json(name = "effort")
    val effort: Int,
    @Json(name = "stat")
    val stat: StatX
)