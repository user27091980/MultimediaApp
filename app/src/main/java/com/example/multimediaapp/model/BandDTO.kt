package com.example.multimediaapp.model

data class BandDTO(

    val id: String,
    val name: String,
    val textInfo: String,
    val headerImage: String,
    val albumImages: List<String>,
    val style: String,
    val recordLabel: String,
    val components: String,
    val discography: List<String>,
    val imageBand: String

)


