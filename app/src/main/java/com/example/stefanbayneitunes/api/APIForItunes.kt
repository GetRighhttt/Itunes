package com.example.stefanbayneitunes.api

import com.example.stefanbayneitunes.model.DataForSongs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiServiceITunes {

    // Getting each URL for use. Each returns a list of songs of each type
    @GET("search")
    fun getArtistsSongs(
        @Query("term") term: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "song",
        @Query("attribute") attribute: String = "artistTerm",
        @Query("lang") lang: String = "en_us",
        @Query("explicit") explicit: String = "Yes"
    ): Call<DataForSongs>

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"
        private var instance: Retrofit? = null

        private fun provideHttpInterceptor(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
            }.build()
            return client
        }

        fun createRetrofit(): Retrofit {
            val gson: GsonConverterFactory = GsonConverterFactory.create()

            if (instance == null) {
                instance = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(gson)
                    .client(provideHttpInterceptor())
                    .build()
            }

            return instance!!
        }

        val retrofit : ApiServiceITunes by lazy {
            ApiServiceITunes.createRetrofit().create(ApiServiceITunes::class.java)
        }
    }
}

