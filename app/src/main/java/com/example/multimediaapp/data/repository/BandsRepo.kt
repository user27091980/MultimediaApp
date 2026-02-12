package com.example.multimediaapp.data.repository

import com.example.multimediaapp.model.BandDTO

class BandsRepo {

    companion object {
    //crear 1 json por banda que cuyon nombre sea bandId.json
        private var currId = 5
        private val bands = ArrayList<BandDTO>(
            listOf(
                BandDTO(
                    "0", "Tool",
                    "Tool es una banda estadounidense de rock y metal alternativo formada\n" +
                            " en Los Ángeles en 1990. Está integrada por el baterista Danny Carey\n" +
                            ", el guitarrista Adam Jones, el vocalista Maynard James Keenan y el bajista\n" +
                            " Justin Chancellor, quien sustituyó a Paul D'Amour en 1995.\n" +
                            "La banda ha vendido más de trece millones de álbumes solo en Estados Unidos\n" +
                            " y otros varios millones alrededor del mundo. Ha sido galardonada con tres premios Grammy.\n"+
                            "Iniciaron tocando un metal progresivo más áspero que el que practican actualmente,\n" +
                            "lo que se refleja en su primer EP, Opiate, -lanzado en 1992- \n" +
                            "en una época en la que dominaba el thrash metal y el rock alternativo.\n" +
                            " Posteriormente alcanzaron la cima del movimiento del metal alternativo con la publicación de Ænima en 1996.\n" +
                            " Sus esfuerzos para unificar la música, \n" +
                            "las artes visuales y mensajes de evolución personal continuaron con Lateralus (2001)\n" +
                            " y con 10,000 Days (2006), con lo que se ganaron los elogios de la crítica y el éxito en todo el mundo.\n" +
                            " Su quinto álbum de estudio, Fear Inoculum, se lanzó el 30 de agosto de 2019 con gran éxito de crítica.\n" +
                            " Antes de su lanzamiento, la banda había vendido más de 13 millones de álbumes solo en Estados Unidos.",

                    "ApiGenerica/data/resources/tool.jpg",
                    albumImages = listOf(),
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
                    "1", "Aphex Twin",
                    "Richard David James (Munster, Irlanda, 18 de agosto de 1971),\n" +
                            " más conocido como Aphex Twin, \n" +
                            "es un DJ y productor irlandés nacionalizado británico.\n" +
                            " Fue descrito por el periódico The Guardian en 2001 como «la figura más innovadora \n" +
                            "e influyente de la música electrónica contemporánea».\n" +
                            " Fundó el sello discográfico Rephlex Records en 1991 junto a Grant Wilson-Claridge.\n" +
                            " En 2021, la publicación Pitchfork lo incluyó en su lista de los 200 artistas\n" +
                            " más influyentes de los últimos 25 años.",
                    "ApiGenerica/data/resources/aphx.png",
                    albumImages = listOf(),
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
                    "2", "NIN",
                    "Nine Inch Nails, comúnmente abreviada como NIN, es una banda estadounidense\n" +
                            " de rock industrial, formada en Cleveland en 1988. Sus miembros son el cantautor,\n" +
                            " multiinstrumentista y productor Trent Reznor y su colaborador frecuente, Atticus Ross.\n" +
                            " Reznor fue anteriormente el único miembro permanente de la banda hasta que Ross fue oficializado en 2016.\n" +
                            " El álbum debut de la banda, Pretty Hate Machine (1989), fue lanzado a través de TVT Records.\n" +
                            " Después de discrepar de TVT sobre cómo se promocionaría el álbum, la banda firmó con Interscope Records\n" +
                            " y lanzó el EP Broken (1992), seguido de los álbumes The Downward Spiral (1994) y The Fragile (1999).\n" +
                            "Nine Inch Nails ha sido nominado a trece premios Grammy y ha ganado premios en tres ocasiones:\n" +
                            " por «Wish» en 1992, «Happiness in Slavery» en 1995 y «As Alive As You Need Me to Be» en 2026.\n" +
                            "Ganador de varios premios por sus bandas sonoras dos Oscars, tres premios Emmy y 1 Bafta ",

                    "ApiGenerica/data/resources/nin.jpg",
                    albumImages = emptyList(),
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
                    "3", "Autechre",
                    "Autechre es un dúo de música electrónica conformado por Rob Brown y Sean Booth\n," +
                            " ambos provenientes de Rochdale,Gran Mánchester.\n" +
                            "Fundado en 1987, son uno de los artistas más destacados de Warp Records,\n" +
                            "un sello conocido por su pionera música electrónica y a través del cual\n" +
                            " todos los álbumes de Autechre han sido publicados." +
                            "Aunque son asociados en gran medida con el IDM (intelligent dance music)\n" +
                            ", Booth y Brown se han mostrado ambivalentes acerca de relacionar su sonido con géneros establecidos.\n" +
                            "Su música ha exhibido un cambio gradual en estética a lo largo de su carrera\n" +
                            ", desde su trabajo anterior con claras raíces en el techno, house y electro,\n" +
                            " hasta álbumes que a menudo son considerados de naturaleza experimental,\n" +
                            "incluyendo complejos patrones de ritmo y melodías tenues.",
                    "ApiGenerica/data/resources/ae0.jpg",
                    albumImages = listOf(),
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
                    "4",
                    "Boards of Canada",
                    "Boards of Canada empieza propiamente su andadura en 1987,\n" +
                            " al juntarse los hermanos Sandinson con amigos de sus alrededores,\n" +
                            " entre los que se encontraba Chris Horne que tendría posteriormente \n" +
                            "una carrera musical aparte bajo el nombre de Christ.\n" +
                            " El nombre lo tomarían del National Film Board of Canada, \n" +
                            "institución de carácter similar a National Geographic, \n" +
                            "de la que Marcus y Michael eran grandes admiradores, \n" +
                            "llegando incluso a samplear partes de sus documentales para usarlas en sus canciones.\n" +
                            "Con el paso del tiempo, el grupo se centraría en los Sandinson\n" +
                            " y fue virando desde lo gótico y oscuro hasta la experimentación y \n" +
                            "psicodelia que les caracterizan. A principios de la década de 1990,\n" +
                            " Boards of Canada era ya un dúo y grababa sus primeras maquetas y composiciones \n" +
                            "en su propio estudio, llamado Hexagon Sun. \n" +
                            "Las primeras grabaciones se distribuían en casetes entre sus amigos de Edimburgo,\n" +
                            " figurando como discográfica su propio sello, Music70.\n" +
                            "En 1995, se publica el primer lanzamiento \"oficial\" de Boards of Canada:\n" +
                            " el EP Twoism a través del ya mencionado sello Music70.\n" +
                            " El disco llegaría a manos de Sean Booth, miembro de Autechre y dueño de Skam Records,\n" +
                            " que inmediatamente les ficha para su sello donde se publica el siguiente EP, Hi Scores.\n" +
                            "\n" +
                            "El paso de Skam a Warp Records fue bastante sencillo, \n" +
                            "ya que Autechre también publicaba en esta última. De esta manera,\n" +
                            " el primer álbum propiamente dicho de los escoceses, Music Has the Right to Children,\n" +
                            " sería publicado desde la primera división de la música electrónica en 1998.\n" +
                            " El disco fue un éxito tanto entre la crítica como en el público, \n" +
                            "siendo considerado por muchos como uno de los mejores discos de IDM de la historia\n" +
                            " y convirtiéndose inmediatamente en un clásico de culto. ",
                    "ApiGenerica/data/resources/boc1.jpg",
                    albumImages = emptyList(),
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
        currId++
        val newId = currId.toString()
        if(bands.add(band.copy(id = newId)))
            onSuccess(bands.last())
        else
            onError()

    }

    fun read(
        id: String,
        onSuccess: (band: BandDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val band = bands.find { it.id == id }

        if (band != null)
            onSuccess(band)
        else
            onError()
    }

    fun delete(
        id: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (bands.removeIf { it.id == id })
            onSuccess()
        else
            onError()
    }
}



