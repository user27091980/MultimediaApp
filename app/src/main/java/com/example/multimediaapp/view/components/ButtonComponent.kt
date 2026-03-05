package com.example.multimediaapp.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BaseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        enabled = enabled,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = text,

        )
    }
}
@Composable
fun ButtonLogin(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    BaseButton(
        text = "Login",
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun ButtonAcept(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseButton(
        text = "Aceptar",
        onClick = onClick,
        modifier = modifier
    )
}

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