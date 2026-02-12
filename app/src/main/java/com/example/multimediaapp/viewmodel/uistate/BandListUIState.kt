package com.example.multimediaapp.viewmodel.uistate

//usar val para que el estado sea inmutable
//incializamos la lista para evitar nulls
data class BandListUIState(val bands: List<BandUIState> = ArrayList())

data class BandUIState(
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

