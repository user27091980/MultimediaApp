package com.example.multimediaapp.viewmodel.uistate

//usar val para que el estado sea inmutable
//incializamos la lista para evitar nulls
data class BandListUIState(val bands: List<BandUIState> =emptyList())

data class BandUIState(
    var id: Int,
    var name: String,

)