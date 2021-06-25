package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class GenerationIi(
    @Json(name = "crystal")
    val crystal: Crystal,
    @Json(name = "gold")
    val gold: Gold,
    @Json(name = "silver")
    val silver: Silver
)