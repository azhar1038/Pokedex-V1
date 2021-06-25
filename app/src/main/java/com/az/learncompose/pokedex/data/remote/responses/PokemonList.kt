package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json

data class PokemonList(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String,
    @Json(name = "previous")
    val previous: Any?,
    @Json(name = "results")
    val results: List<Result>
)