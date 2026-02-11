package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.model.BandDTO
import com.example.multimediaapp.data.model.TagsDTO

class TagsRepo{
    companion object {
        val tags = ArrayList<TagsDTO>(
            listOf(
                TagsDTO(0, "metal progresivo","BGM","Maynard James Keenan, Danny Carey, Justin Chancellor, Adam Jones"),
                TagsDTO(1, "idm","Warp Records","Richar D. James"),
                TagsDTO(2, "industrial, rock alternativo","null corp","Trent Reznor"),
                TagsDTO(3, "experimental","Warp Records","Sean Booth,Robert Brown")
            )
        )
        var currId = 4
    }

    //crud

    fun readAll(onSuccess: (List<TagsDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(tags)
    }

    fun crear(
        est: TagsDTO,
        onSuccess: (bandCreado: TagsDTO) -> Unit,
        onError: () -> Unit
    ) {

        if (tags.add(est.copy(id = currId++)))
            onSuccess(tags.last())
        else
            onError()

    }

    fun read(
        id: Int,
        onSuccess: (createdBand: TagsDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val tag = tags.find { it.id == id }
        if (tag != null)
            onSuccess(tag)
        else
            onError()
    }

    fun delete(
        id: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (tags.removeIf { it.id == id })
            onSuccess()
        else
            onError()
    }


}
