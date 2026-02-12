package com.example.multimediaapp.viewmodel.uistate

//usar val para que el estado sea inmutable
//incializamos la lista para evitar nulls
data class BandListUiState(val bands: List<BandUiState> = ArrayList())

data class BandUiState(
    val id: String,
    val name: String,
    val textInfo: String,
    val headerImage: String,
    val albumImages: List<String>,
    val style: String,
    val recordLabel: String,
    val components: String,
    val discography: List<String>

)

