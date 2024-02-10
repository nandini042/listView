package com.example.anime


import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("top/anime")
    fun getAnime(): Call<TopAnime>
}