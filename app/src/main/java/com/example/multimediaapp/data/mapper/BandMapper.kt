package com.example.multimediaapp.data.mapper

import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.model.MainDTO

fun BandDTO.toMainDTO(): MainDTO {
    return MainDTO(
        id = this.id,
        bandName = this.name,
        imageBand = this.banner.ifEmpty {
            this.albumImages.firstOrNull() ?: ""
        }
    )
}