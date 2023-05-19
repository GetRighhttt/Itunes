package com.example.stefanbayneitunes.api

import com.example.stefanbayneitunes.model.DataForSongs
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceITunes {

    // Getting each URL for use. Each returns a list of songs of each type
    @GET("search?")
    fun getArtistsSongs(
        @Query("term") term: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "song",
        @Query("attribute") attribute: String = "artistTerm",
        @Query("lang") lang: String = "en_us",
        @Query("explicit") explicit: String = "Yes"
    ): Call<DataForSongs>

    companion object{
        private const val BASE_URL = "https://itunes.apple.com/"
        private var instance: Retrofit? = null

        fun createRetrofit(): Retrofit{
            if(instance == null){
                instance = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return instance!!
        }
    }
}

