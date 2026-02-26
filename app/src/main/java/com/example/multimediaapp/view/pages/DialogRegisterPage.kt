package com.example.multimediaapp.view.pages

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multimediaapp.viewmodel.vm.DialogVM

/**
 * @author Andrés
 */

//ejemplo dialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogPage(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    vm: DialogVM = viewModel()
) {

    val uiState by vm.uiState.collectAsState()

    AlertDialog(
        onDismissRequest = {vm.hideDialog()},
        title = {
            Text("¿Confirmar registro?")
        },
        confirmButton = {

            TextButton(onClick = { vm.confirmAction() }) {

                Text("sí")
            }

        },
        dismissButton = {
            TextButton(onClick = {vm.hideDialog()}) {
                Text("no")
            }
        }
    )
}


@Preview
@Composable
fun PreviewDialog(){

    DialogPage(onConfirm = { },
        onCancel = { })
}