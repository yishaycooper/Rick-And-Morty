package com.example.rickandmorty.network

import com.squareup.moshi.Json

data class Location(
    @Json(name = "created")
    val created: String,
    @Json(name = "dimension")
    val dimension: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "residents")
    val residents: List<String>,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)