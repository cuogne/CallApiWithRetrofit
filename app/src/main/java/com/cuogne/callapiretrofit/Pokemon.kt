package com.cuogne.callapiretrofit

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val results: List<Results>
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Results(
    val name: String,
    val url: String
)

data class Sprites(
    @SerializedName("front_default")
    val img: String
)