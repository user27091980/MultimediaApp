package com.example.multimediaapp.data.model

data class BandDTO(

    var id: Int,
    var name: String,
    var textInfo: String,
    var headerImage: String,
    var imageAlbums: List<String>,
    var style: String,
    var recordLabel: String,
    var components: String,
    var discography: List<String>

)


