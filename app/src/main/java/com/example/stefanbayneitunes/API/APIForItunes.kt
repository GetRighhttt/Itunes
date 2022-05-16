package com.example.stefanbayneitunes.API

import com.example.stefanbayneitunes.DataClass.AllDataOfTheSongs
import com.example.stefanbayneitunes.DataClass.DataForSongs
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceITunes {

    @GET("search?term=hiphop&amp;media=music&entity=song&limit=50")
    fun getHipHopSongs(
    ): Call<DataForSongs>

    @GET("search?term=jazz&amp;media=music&entity=song&limit=50")
    fun getJazzSongs(
    ): Call<DataForSongs>

    @GET("search?term=gospel&amp;media=music&entity=song&limit=50")
    fun getGospelSongs(
    ): Call<DataForSongs>

    companion object{
        private const val BASE_URL = "https://itunes.apple.com/"
        var instance: Retrofit? = null

        fun createRetrofit(): Retrofit{
            if(instance == null){
                instance = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return  instance!!
        }
    }
}

