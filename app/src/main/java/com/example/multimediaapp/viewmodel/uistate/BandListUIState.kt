package com.example.multimediaapp.viewmodel.uistate




//usar val para que el estado sea inmutable
//incializamos la lista para evitar nulls
data class BandListUIState(val bands: List<BandUIState> = ArrayList())

data class BandUIState(
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

