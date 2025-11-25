package com.cuogne.callapiretrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    // @GET - GET request
    // @POST - POST request
    // @PUT - PUT request
    // @DELETE - DELETE request

    // api base: https://api.com
    // @GET("test/{id}"): Lay du lieu cua api base co endpoint la test/{id}
    // -> https://api.com/test/{id}

    // Dung Path("id") de truyen vao 1 id vao ham suspend
    // suspend fun get(@Path("id") id: String): Response<res>

    // @Query khi dung de loc, sap xep, phan trang (sau dau cham hoi ?)
    // @Query("limit") limit: Int,

    // @GET("test")
    // suspend fun getQuery(
    //     @Query("limit") limit: Int,
    //     @Query("offset") offset: Int
    // ): Response<res>
    // -> https://api.com/test?limit={limit}&offset={offset}

    // Gui json object -> @Body
    // data class JsonObject(val text: String, val number: Int)
    // @POST("test")
    // suspend fun post(@Body body: JsonObject): Response<res>
    // Retrofit tu bien class JsonObject thanh json de gui len server

    // Ghi de luon base url ->
    // su dung @GET nhma khong co endpoint
    // truyen vao suspend function 1 @Url url: String
    // day la toan bo url se ghi de base url luc call -> call 1 api khac

    // Khi su dung: su dung trong Dispatcher.IO (khong dc call api o main thread)
    // val response = RetrofitClient.instance.{ten suspend function tuong ung}(tham so cua function)
    // val response = RetrofitClient.instance.get("3") -> goi toi endpoint @GET("test/{id}") -> test/3
    // check bang isSuccessful va lay data = response.body()

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