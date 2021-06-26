package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonList(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "next")
    val next: String = "",
    @Json(name = "previous")
    val previous: Any? = null,
    @Json(name = "results")
    val results: List<Result> = listOf()
)