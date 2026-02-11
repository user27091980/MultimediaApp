package com.example.multimediaapp.data.entity

import com.google.gson.annotations.SerializedName


/*
Representa un modelo de datos simple.
Kotlin generará automáticamente equals(), hashCode(), toString(), copy().
Ideal para JSON, RecyclerView, Room, etc.
 */

data class BandEntity(

    val id: Int,
    @SerializedName("nombre") val name: List<String>,

)
