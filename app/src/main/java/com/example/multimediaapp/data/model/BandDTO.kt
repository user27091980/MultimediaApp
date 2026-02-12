package com.example.multimediaapp.data.model

data class BandDTO(

    val id: Int,
    val name: String,
    val textInfo: String,
    val headerImage: String,
    val albumImages: List<String>,
    val style: String,
    val recordLabel: String,
    val components: String,
    val discography: List<String>

)


