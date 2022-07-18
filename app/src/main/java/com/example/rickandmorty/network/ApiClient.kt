package com.example.rickandmorty.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object ApiClient {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory (MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val locationService: LocationService by lazy {
        retrofit.create(LocationService::class.java)
    }
}

// USING CALL
//interface ApiService {
//    @GET("character")
//    fun fetchCharacters(@Query("page") page: String): Call<CharacterResponse>
//}

// USING COROUTINES
interface ApiService {
    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: String): Response<CharacterResponse>
}

interface LocationService {

    @GET("location/{character-id}")
    suspend fun fetchLocation(
        @Path("character-id") characterId: Int
    ) : Response<Location>
}

