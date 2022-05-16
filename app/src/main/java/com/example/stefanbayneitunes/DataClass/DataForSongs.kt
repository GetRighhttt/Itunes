package com.example.stefanbayneitunes.DataClass

data class DataForSongs(
    val results : List<DataForSongs>
)

data class AllDataOfTheSongs(
    val artistName : String,
    val collectionName : String,
    val trackName : String,
    val artworkUrl60 : String,
    val trackPrice : String,
    val previewUrl : String
)
