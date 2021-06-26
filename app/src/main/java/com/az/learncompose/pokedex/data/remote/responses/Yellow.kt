package com.az.learncompose.pokedex.data.remote.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Yellow(
    @Json(name = "back_default")
    val backDefault: String = "",
    @Json(name = "back_gray")
    val backGray: String = "",
    @Json(name = "front_default")
    val frontDefault: String = "",
    @Json(name = "front_gray")
    val frontGray: String = ""
)