package com.example.multimediaapp.view.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable base para todos los botones de la aplicación.
 *
 * Permite reutilizar un estilo consistente:
 * - Ancho completo por defecto
 * - Bordes redondeados
 * - Colores del tema
 * - Estado habilitado / deshabilitado
 *
 * @param text Texto que se mostrará en el botón
 * @param onClick Acción que se ejecuta al pulsar el botón
 * @param modifier Modifier opcional para personalizar tamaño, padding u otras propiedades
 * @param enabled Boolean que indica si el botón está habilitado (true) o deshabilitado (false)
 */
@Composable
fun BaseButton(
    text: String,   // Texto que se mostrará en el botón
    onClick: () -> Unit,// Acción al pulsar el botón
    modifier: Modifier = Modifier, // Modifier opcional para composición externa
    enabled: Boolean = true // Modifier opcional para composición externa
) {

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),// Siempre ocupa todo el ancho disponible
        shape = RoundedCornerShape(20.dp),// Bordes redondeados de 20dp
        enabled = enabled,// Estado habilitado/deshabilitado
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Fondo del botón
            contentColor = MaterialTheme.colorScheme.onPrimary// Color del texto
        )
    ) {
        Text(
            text = text,

            )
    }
}


/**
 * Botón de Login.
 *
 * Reutiliza BaseButton con texto fijo "Login".
 */
@Composable
fun ButtonLogin(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * Botón específico para Aceptar
     *
     * Reutiliza BaseButton con texto predefinido "Aceptar"
     */
    BaseButton(
        text = "Login",
        onClick = onClick,
        modifier = modifier
    )
}

/**
 * Botón específico para Aceptar
 *
 * Reutiliza BaseButton con texto predefinido "Aceptar"
 */
@Composable
fun ButtonAccept(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Aceptar",
        onClick = onClick,
        modifier = modifier
    )
}

/**
 * Botón específico para cancelar
 *
 * Reutiliza BaseButton con texto predefinido "Cancelar"
 */
@Composable
fun ButtonCancel(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Cancelar",
        onClick = onClick,
        modifier = modifier
    )
}

/**
 * Botón específico para Registar
 *
 * Reutiliza BaseButton con texto predefinido "Registrar"
 */
@Composable
fun ButtonRegister(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Registrar",
        onClick = onClick,
        modifier = modifier
    )
}
/**
 * Botón para seleccionar una imagen de la galería.
 *
 * @param text Texto del botón
 * @param onImageSelected Callback con la Uri seleccionada
 */
@Composable
fun ButtonImage(
    text: String,
    onImageSelected: (Uri?) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        onImageSelected(uri)
    }

    BaseButton(
        text = text,
        onClick = { launcher.launch("image/*") }
    )
}

/**
 * Botón para seleccionar múltiples imágenes.
 *
 * @param text Texto del botón
 * @param onImagesSelected Callback con lista de Uri
 */
@Composable
fun ButtonGallery(
    text: String,
    onImagesSelected: (List<Uri>) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        onImagesSelected(uris)
    }

    BaseButton(
        text = text,
        onClick = { launcher.launch("image/*") }
    )
}
/*
BaseButton: botón genérico reutilizable con estilo consistente (colores, bordes, ancho completo).

Botones específicos (ButtonLogin, ButtonAcept, etc.) → reutilizan BaseButton con texto fijo,
 lo que evita repetir código y mantiene coherencia visual.

modifier  permite añadir padding, peso o cualquier otra modificación externa en Compose.

enabled se puede desactivar el botón si es necesario (por ejemplo, mientras se hace login).
 */
/*
 * Este archivo define un conjunto de componentes reutilizables de botones
 * en Jetpack Compose, diseñados para mantener un estilo consistente en toda la aplicación.
 *
 * COMPONENTE PRINCIPAL:
 *
 * BaseButton
 * - Es un botón genérico reutilizable.
 * - Recibe:
 *   - text: texto que se muestra en el botón
 *   - onClick: acción al pulsar el botón
 *   - modifier: permite personalizar la UI desde fuera
 *   - enabled: controla si el botón está activo o deshabilitado
 *
 * - Características visuales:
 *   - Usa FilledTonalButton de Material 3
 *   - Ocupa todo el ancho disponible (fillMaxWidth)
 *   - Bordes redondeados (RoundedCornerShape)
 *   - Colores definidos por el MaterialTheme
 *
 * VENTAJAS DE BaseButton:
 * - Evita duplicación de código
 * - Mantiene coherencia visual
 * - Facilita cambios globales de estilo
 *
 * BOTONES ESPECÍFICOS:
 *
 * ButtonLogin, ButtonAccept, ButtonCancel, ButtonRegister
 * - Son componentes que reutilizan BaseButton
 * - Solo cambian el texto del botón
 * - Se usan para acciones concretas de la UI
 *
 * BOTONES CON SELECCIÓN DE IMÁGENES:
 *
 * ButtonImage
 * - Permite seleccionar una imagen desde la galería
 * - Usa rememberLauncherForActivityResult
 * - Lanza un intent para obtener contenido tipo "image/*"
 * - Devuelve una Uri a través del callback onImageSelected
 *
 * ButtonGallery
 * - Permite seleccionar múltiples imágenes
 * - Utiliza ActivityResultContracts.GetMultipleContents()
 * - Devuelve una lista de Uri
 *
 * CONCEPTOS IMPORTANTES:
 *
 * rememberLauncherForActivityResult:
 * - Gestiona la respuesta de actividades externas (como galería)
 * - Mantiene el launcher durante recomposiciones
 *
 * Uri:
 * - Representa la ubicación del recurso seleccionado (imagen)
 *
 * USO:
 * - Estos botones se utilizan en formularios, pantallas de registro,
 *   carga de imágenes, etc.
 *
 * BENEFICIOS GENERALES:
 * - Código modular y reutilizable
 * - Separación de responsabilidades
 * - UI consistente y fácil de mantener
 */