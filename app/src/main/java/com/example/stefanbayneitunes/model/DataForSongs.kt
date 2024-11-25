package com.example.stefanbayneitunes.model

data class DataForSongs(
    val results: List<AllDataOfTheSongs>
)

data class AllDataOfTheSongs(
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val artworkUrl60: String,
    val trackPrice: String?,
    val previewUrl: String,
    val collectionPrice: String,
    val country: String,
    val currency: String,
    val primaryGenreName: String
)
