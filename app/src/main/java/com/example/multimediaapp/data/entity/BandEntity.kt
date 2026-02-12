package com.example.multimediaapp.data.entity

import android.media.Image
import com.google.gson.annotations.SerializedName


/*
Representa un modelo de datos simple.
Kotlin generará automáticamente equals(), hashCode(), toString(), copy().
Ideal para JSON, RecyclerView, Room, etc.
 */

data class BandEntity(

    val id: Int,
    @SerializedName("nombre") val name: List<String>,
    @SerializedName("texto") val textInfo: String,
    @SerializedName("cabecera") val picHead: String,
    @SerializedName ("estilo") val style: String,
    @SerializedName ("discográfica") val discog: String,
    @SerializedName ("componentes") val components: String,
    @SerializedName ("discografía") val discography: List<String>,
    @SerializedName("discos") val imageAlbums: List<String>

)
