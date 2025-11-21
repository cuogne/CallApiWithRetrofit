package com.cuogne.callapiretrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
//    @GET("posts/1")
//    suspend fun getPost(): Response<Post>

//    @GET("pokemon/{id}")
//    suspend fun getPokemon(Path("id") id: String): Response<Pokemon>

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<Pokemon>

    @GET
    suspend fun getDetailPokemon(
        @Url url: String
    ): Response<PokemonDetail>

    // for call (not coroutine)
    @GET("pokemon")
    fun getPoke(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<Pokemon>

}