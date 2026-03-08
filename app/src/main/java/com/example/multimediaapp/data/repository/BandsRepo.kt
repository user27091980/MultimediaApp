// Define el paquete donde se encuentra la clase.
// Pertenece a la capa de datos (data) dentro del módulo repository.
package com.example.multimediaapp.data.repository
// Importa el modelo que utiliza la app (capa dominio / presentación).
import com.example.multimediaapp.model.BandDTO

// Clase repositorio que simula una fuente de datos en memoria.
// Aquí se gestiona el CRUD(CREATE, READ, UPDATE, DELETE) de bandas.
class BandsRepo {
    // Companion object → funciona como un bloque estático en Java.
    // Las propiedades aquí definidas pertenecen a la clase y no a la instanci
    companion object {
        // Variable que guarda el último ID utilizado.
        // Se incrementa cuando se crea una nueva banda.
        private var currId = 5

        // Lista mutable que almacena las bandas en memoria.
        // Se inicializa con datos "hardcodeados".
        private val bands = ArrayList<BandDTO>(

            listOf(
                // ----------- TOOL -----------
                BandDTO(
                    "0", "Tool",
                    "Tool es una banda estadounidense de rock y metal alternativo formada\n" +
                            " en Los Ángeles en 1990. Está integrada por el baterista Danny Carey\n" +
                            ", el guitarrista Adam Jones, el vocalista Maynard James Keenan y el bajista\n" +
                            " Justin Chancellor, quien sustituyó a Paul D'Amour en 1995.\n" +
                            "La banda ha vendido más de trece millones de álbumes solo en Estados Unidos\n" +
                            " y otros varios millones alrededor del mundo. Ha sido galardonada con tres premios Grammy.\n" +
                            "Iniciaron tocando un metal progresivo más áspero que el que practican actualmente,\n" +
                            "lo que se refleja en su primer EP, Opiate, -lanzado en 1992- \n" +
                            "en una época en la que dominaba el thrash metal y el rock alternativo.\n" +
                            " Posteriormente alcanzaron la cima del movimiento del metal alternativo con la publicación de Ænima en 1996.\n" +
                            " Sus esfuerzos para unificar la música, \n" +
                            "las artes visuales y mensajes de evolución personal continuaron con Lateralus (2001)\n" +
                            " y con 10,000 Days (2006), con lo que se ganaron los elogios de la crítica y el éxito en todo el mundo.\n" +
                            " Su quinto álbum de estudio, Fear Inoculum, se lanzó el 30 de agosto de 2019 con gran éxito de crítica.\n" +
                            " Antes de su lanzamiento, la banda había vendido más de 13 millones de álbumes solo en Estados Unidos.",

                    headerImage = "http://10.0.2.2:5131/images/tool",
                    albumImages = listOf(
                        "http://10.0.2.2:5131/images/tool3",
                        "http://10.0.2.2:5131/images/tool4",
                        "http://10.0.2.2:5131/images/tool5",
                        "http://10.0.2.2:5131/images/tool6",
                        "http://10.0.2.2:5131/images/tool7",
                        "http://10.0.2.2:5131/images/tool8"
                    ),
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
                    albumLinks = listOf(
                        "https://www.youtube.com/playlist?list=OLAK5uy_lGJLMX42JRmpbF-DFZrUBLL7ymhQ9AiCY",
                        "https://www.youtube.com/playlist?list=OLAK5uy_kPpSQAo2ycoWy2VJY4BAqjW0lKyrsi4ro",
                        "https://www.youtube.com/playlist?list=OLAK5uy_krq_ZS_QzdAelhJqlSSkpFNDRnff6CQgs",
                        "https://www.youtube.com/playlist?list=OLAK5uy_lKfwlbgU4wmlqdKp4GdNO7W6vlG-fhDaM",
                        "https://www.youtube.com/playlist?list=OLAK5uy_l7KrE4YuWhZsfCdXlLJERHyk4YlwydukA",
                        "https://www.youtube.com/playlist?list=OLAK5uy_k_Z0-DYw0_cGPvZm6icNUGbefRT4wktdY"
                    ),
                    headerLink = "https://www.toolband.com/home?k=4d58aff6"
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
                    headerImage = "http://10.0.2.2:5131/images/aphx",
                    albumImages = listOf(
                        "http://10.0.2.2:5131/images/aphx1",
                        "http://10.0.2.2:5131/images/aphx2",
                        "http://10.0.2.2:5131/images/aphx3",
                        "http://10.0.2.2:5131/images/aphx4",
                        "http://10.0.2.2:5131/images/aphx6",
                        "http://10.0.2.2:5131/images/aphx7",
                        "http://10.0.2.2:5131/images/aphx8"
                    ),
                    style = "idm",
                    recordLabel = "Warp Records",
                    components = "Richard D. James",
                    discography = listOf(
                        "Selected Ambient Works",
                        "Come to daddy",
                        "Drukqs",
                        "Hangable Auto Bulb",
                        "...i care because you do"

                    ),
                    albumLinks = listOf(
                        "https://www.youtube.com/watch?v=sWcLccMuCA8&list=PLmGuMvhZ3-aPMfmoKpQ6WE3bSTkBFuaSl",
                        "https://www.youtube.com/playlist?list=OLAK5uy_kZb_rG2RQfOcyfRPpp3ZTThnCRHDzHQO0",
                        "https://www.youtube.com/playlist?list=OLAK5uy_kqtENKafwOg96cqWcB0BVuFCf2iTroYqk",
                        "https://www.youtube.com/watch?v=CWwgWWfb2u4&list=PLk5ImplHgDw0s4mwh1D9r_Mo7sMZIdyEn",
                        "https://www.youtube.com/playlist?list=OLAK5uy_lkdpFMWST_gPGQ9hKW6Pvum5ybfLhOGjs"
                    ),
                    headerLink = "https://aphextwin.warp.net/"
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

                    headerImage = "http://10.0.2.2:5131/images/nin",
                    albumImages = listOf(
                        "http://10.0.2.2:5131/images/nin2",
                        "http://10.0.2.2:5131/images/nin3",
                        "http://10.0.2.2:5131/images/nin4",
                        "http://10.0.2.2:5131/images/nin5",
                        "http://10.0.2.2:5131/images/nin6"
                    ),
                    style = "industrial, alternative",
                    recordLabel = "nothing records, null corp",
                    components = "Trent Reznor",
                    discography = listOf(
                        "pretty hate machine",
                        "broken",
                        "the downward spiral",
                        "the fragile",
                        "with teeth"
                    ),
                    albumLinks = listOf(
                        "https://www.youtube.com/playlist?list=OLAK5uy_kb5JAPIaEHswetSZRxEguGMBMXUlMwXC4",
                        "https://www.youtube.com/playlist?list=OLAK5uy_ntyTKmPtSuVGCKd7FPfc7ep68vIobe9VE",
                        "https://www.youtube.com/playlist?list=OLAK5uy_kxE6rMX-COuLZaPTSRr2Rqr-UOkPAEmbs",
                        "https://www.youtube.com/watch?v=SWCYAS_p8zU&list=PLnif9Rfb5Adl4SmrXY_L4GshBSYCecEX3",
                        "https://www.youtube.com/playlist?list=OLAK5uy_mNlb0ntqQSw8iFKOBO1bDlGxlRKsW_MAA"

                    ),
                    headerLink = "https://www.nin.com/"

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
                    headerImage = "http://10.0.2.2:5131/images/ae0",
                    albumImages = listOf(
                        "http://10.0.2.2:5131/images/ae2",
                        "http://10.0.2.2:5131/images/ae3",
                        "http://10.0.2.2:5131/images/ae4",
                        "http://10.0.2.2:5131/images/ae5",
                        "http://10.0.2.2:5131/images/ae6"
                    ),
                    style = "experimental",
                    recordLabel = "Warp Records",
                    components = "Sean Booth,Robert Brown",
                    discography = listOf(
                        "Chiastic Slide",
                        "Exai",
                        "Incunabula",
                        "NTS Sessions",
                        "AE_2022"
                    ),
                    albumLinks = listOf(
                        "https://www.youtube.com/watch?v=kmf29LAjJGI&list=PLQNcb2RwdcZKodeo9nrQkeNn2LnCgCZMW",
                        "https://www.youtube.com/playlist?list=OLAK5uy_mIkZBmozb7s6SmnlOVhJoGDiEMmK0h1dM",
                        "https://www.youtube.com/playlist?list=OLAK5uy_nqFGUvQ82a8-29dCHQWoEhI9UeoA1tpDk",
                        "https://www.youtube.com/watch?v=Wr6vARWre-Y&list=PL4wjgXyj5LGQWHfBI38Lizk13S8zm7Ibs",
                        "https://www.youtube.com/playlist?list=OLAK5uy_nKTeSB_e8VUv_ZZl8Y36TqAp9GWBadhnU"

                    ),
                    headerLink = "https://autechre.warp.net/"

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
                    headerImage = "http://10.0.2.2:5131/images/boc1",
                    albumImages = listOf(
                        "http://10.0.2.2:5131/images/boc2",
                        "http://10.0.2.2:5131/images/boc3",
                        "http://10.0.2.2:5131/images/boc4",
                        "http://10.0.2.2:5131/images/boc5",
                        "http://10.0.2.2:5131/images/boc6"
                    ),
                    style = "idm, downtempo",
                    recordLabel = "Warp records, Skam Records",
                    components = "Marcus Eoin, Mike Sandison",
                    discography = listOf(
                        "High Scores",
                        "Music has the right to Children",
                        "Geogaddi",
                        "Tomorrow´s harvest",
                        "The Campfire headphase"
                    ),
                    albumLinks = listOf(
                        "https://www.youtube.com/watch?v=EuvMNQ7IjNY&list=RDEuvMNQ7IjNY&start_radio=1",
                        "https://www.youtube.com/playlist?list=OLAK5uy_lcbBHZNY1YP-yJMLufIAuk6s1NXF0N_bU",
                        "https://www.youtube.com/playlist?list=OLAK5uy_kBIHneSc23tMiNaX68rurYceNxzwtBH84",
                        "https://www.youtube.com/playlist?list=OLAK5uy_lbgL-BgNt1VLeoJN1QDMPfBWwFn5zsccA",
                        "https://www.youtube.com/playlist?list=OLAK5uy_nwT_ryJ0N8-TmfqftIrZkcLGGiE_JMT7s"

                    ),
                    headerLink = "https://warp.net/artists/boards-of-canada"

                )

            )
        )
    }

    // -------------------------------------------------
    // CRUD (Create, Read, Update, Delete)
    // -------------------------------------------------

    // READ ALL
    // Devuelve todas las bandas.
    // Se usan callbacks para simular comportamiento asíncrono.

    fun readAll(onSuccess: (List<BandDTO>) -> Unit, onError: () -> Unit) {
        //Se devuelve una copia inmutable de la lista
        // para evitar modificar la lista original.
        onSuccess(bands.toList())//evitamos devolver la lista real mutable
    }

    // CREATE
    // Añade una nueva banda a la lista.
    fun create(
        band: BandDTO,
        onSuccess: (createdBand: BandDTO) -> Unit,
        onError: () -> Unit
    ) {
        //Incrementamos el ID
        currId++
        val newId = currId.toString()
        // Se añade una copia del objeto con el nuevo ID
        if (bands.add(band.copy(id = newId)))
            onSuccess(bands.last())// Devuelve la banda creada
        else
            onError()

    }

    // READ (por ID)
    // Busca una banda concreta por su ID.
    fun read(
        id: String,
        onSuccess: (band: BandDTO?) -> Unit,
        onError: () -> Unit
    ) {
        // Busca el primer elemento cuyo id coincida
        val band = bands.find { it.id == id }

        if (band != null)
            onSuccess(band)
        else
            onError()
    }
    // DELETE
    // Elimina una banda por ID.
    fun delete(
        id: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        // removeIf devuelve true si eliminó algún elemento
        if (bands.removeIf { it.id == id })
            onSuccess()
        else
            onError()
    }

    fun update(
        updateBand: BandDTO,
        onSuccess: (BandDTO) -> Unit,
        onError: () -> Unit
    ) {
        // Buscamos el índice de la banda con el mismo ID
        val index = bands.indexOfFirst { it.id == updateBand.id }

        if (index != -1) {
            // Reemplazamos el objeto en esa posición
            bands[index] = updateBand
            onSuccess(updateBand)
        } else {
            onError()
        }
    }

}

/**EXPLICACIÓN ARQUITECTURA
¿Qué es BandsRepo?

Es un repositorio en memoria que simula una base de datos o API.

En una app real podría conectarse a:

Retrofit (API REST)

Room (Base de datos local)

Firebase

MongoDB

etc.

¿Por qué usar companion object?

Porque:

La lista bands se comparte entre todas las instancias.

Simula un almacenamiento estático.

¿Por qué usar copy() en create?
band.copy(id = newId)


Porque BandDTO es una data class y copy() permite:

Clonar el objeto

Cambiar solo algunos campos

Mantener inmutabilidad

¿Por qué devolver bands.toList()?

Para evitar que desde fuera puedan hacer:

bands.add(...)


y modificar la lista interna.

¿Qué es CRUD?

CRUD es un acrónimo que representa las 4 operaciones básicas que se pueden hacer sobre datos en programación y bases de datos:

Letra	Significado	Qué hace
C	Create	Crear datos
R	Read	Leer datos
U	Update	Actualizar datos
D	Delete	Eliminar datos
Create (Crear)

Permite agregar un nuevo registro.

Ejemplo:

Crear una nueva banda en tu app.

Insertar un usuario en una base de datos.

Añadir un producto a una tienda online.

En Kotlin podría verse así:

fun create(band: BandDTO)

Read (Leer)

Permite consultar datos existentes.

Puede ser:

Leer todos los registros.

Leer uno específico por ID.

Ejemplo:

fun readAll()
fun read(id: String)

Update (Actualizar)

Permite modificar un registro existente.

Ejemplo:

Cambiar el nombre de una banda.

Actualizar el email de un usuario.

fun update(band: BandDTO)

Delete (Eliminar)

Permite borrar un registro.

Ejemplo:

fun delete(id: String)

¿Dónde se usa CRUD?

CRUD se usa en:

Aplicaciones móviles (Android/iOS)

APIs REST

Bases de datos (MySQL, SQLite, MongoDB)

Firebase

Sistemas de gestión (ERP, CRM)

CRUD en una API REST

En desarrollo web normalmente se asocia a métodos HTTP:

CRUD	HTTP
Create	POST
Read	GET
Update	PUT / PATCH
Delete	DELETE


 */