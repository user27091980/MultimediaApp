package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.model.AlbumsDTO


class AlbumsRepo {

    companion object {
        val albums = ArrayList<AlbumsDTO>(
            listOf(
                AlbumsDTO(0),
                AlbumsDTO(1),
                AlbumsDTO(2),
                AlbumsDTO(3)
            )
        )
        var currId = 4
    }

    //crud

    fun readAll(onSuccess: (List<AlbumsDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(albums)
    }

    fun crear(
        est: AlbumsDTO,
        onSuccess: (albumCreado: AlbumsDTO) -> Unit,
        onError: () -> Unit
    ) {

        if (albums.add(est.copy(id = currId++)))
            onSuccess(albums.last())
        else
            onError()

    }

    fun read(
        id: Int,
        onSuccess: (createdAlbum: AlbumsDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val album = albums.find { it.id == id }
        if (album != null)
            onSuccess(album)
        else
            onError()
    }

    fun delete(
        id: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (albums.removeIf { it.id == id })
            onSuccess()
        else
            onError()
    }
}




