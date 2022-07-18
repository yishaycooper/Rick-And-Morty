package com.example.rickandmorty.network
import com.squareup.moshi.Json
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Character  (

    @Json(name = "created")
    val created: String,
    @Json(name = "episode")
    val episode: List<String>,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "location")
    val location: Location,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin")
    val origin: Origin,
    @Json(name = "species")
    val species: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
) : Parcelable {
    @Parcelize
    data class Location(
        @Json(name = "name")
        val name: String,
        @Json(name = "url")
        val url: String
    ) : Parcelable
    @Parcelize
    data class Origin(
        @Json(name = "name")
        val name: String,
        @Json(name = "url")
        val url: String
    ) : Parcelable
}

data class CharacterResponse (
    @Json(name="results")
    val results: List<Character>
)
