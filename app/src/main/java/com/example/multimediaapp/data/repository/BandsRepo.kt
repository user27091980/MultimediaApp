package com.example.multimediaapp.data.repository

import com.example.multimediaapp.data.model.BandDTO

class BandsRepo {

    companion object {

        private var currId = 5
        private val bands = ArrayList<BandDTO>(
            listOf(
                BandDTO(
                    0, "Tool", "", "",
                    imageAlbums = emptyList(),
                    style = "metal progresivo",
                    recordLabel = "BGM",
                    components = "Maynard James Keenan, Danny Carey, Justin Chancellor, Adam Jones",
                    discography = listOf(
                        "opiate",
                        "undertow",
                        "aenima",
                        "lateralus",
                        "10000 days",
                        "fear inoculum"
                    ),

                    ),
                BandDTO(
                    1, "Aphex Twin", "", "",
                    imageAlbums = emptyList(),
                    style = "idm",
                    recordLabel = "Warp Records",
                    components = "Richard D. James",
                    discography = listOf(
                        "Selected Ambient Works",
                        "Come to daddy",
                        "Drukqs"
                    ),
                ),
                BandDTO(
                    2, "NIN", "", "",
                    imageAlbums = emptyList(),
                    style = "industrial, alternative",
                    recordLabel = "nothing records, null corp",
                    components = "Trent Reznor",
                    discography = listOf(
                        "pretty hate machine",
                        "broken",
                        "the downward spiral",
                        "the fragile"
                    ),
                ),
                BandDTO(
                    3, "Autechre", "", "",
                    imageAlbums = emptyList(),
                    style = "experimental",
                    recordLabel = "Warp Records",
                    components = "Sean Booth,Robert Brown",
                    discography = listOf(
                        "Chiastic Slide",
                        "Exai",
                        "Incunabula",
                        "NTS Sessions"
                    ),
                ),
                BandDTO(
                    4,
                    "Boards of Canada", "", "",
                    imageAlbums = emptyList(),
                    style = "idm, downtempo",
                    recordLabel = "Warp records, Skam Records",
                    components = "Marcus Eoin, Mike Sandison",
                    discography = listOf(
                        "High Scores",
                        "Music has the right to Children",
                        "Geogaddi",
                        "Tomorrow´s harvest"
                    ),
                )

            )
        )
    }

    //crud

    fun readAll(onSuccess: (List<BandDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(bands.toList())//evitamos devolver la lista real mutable
    }

    fun create(
        band: BandDTO,
        onSuccess: (createdBand: BandDTO) -> Unit,
        onError: () -> Unit
    ) {

        bands.add(band.copy(id = currId++))
        onSuccess(bands.last())

    }

    fun read(
        id: Int,
        onSuccess: (band: BandDTO?) -> Unit,
        onError: () -> Unit
    ) {

        onSuccess(bands.find { it.id == id })
    }

    fun delete(
        id: Int,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (bands.removeIf { it.id == id })
            onSuccess()
        else
            onError()
    }
}



