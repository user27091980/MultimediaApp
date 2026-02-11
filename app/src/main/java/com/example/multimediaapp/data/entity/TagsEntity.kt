package com.example.multimediaapp.data.entity

import com.google.gson.annotations.SerializedName

data class TagsEntity(

    var id: Int,
    @SerializedName ("estilo") var style: String,
    @SerializedName ("discográfica") var discog: String,
    @SerializedName ("componentes") var components: String
)
