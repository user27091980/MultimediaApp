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
import androidx.navigation.NavController
import com.example.multimediaapp.viewmodel.vm.DialogVM

/**
 * @author Andrés
 */

//ejemplo dialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogRegisterScreen(
    navController: NavController,
    vm: DialogVM = viewModel(),
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {

    val uiState by vm.uiState.collectAsState()

    AlertDialog(
        onDismissRequest = {vm.hideDialog()},
        title = {
            Text("¿Confirmar registro?")
        },
        confirmButton = {

            TextButton(onClick = { vm.confirmAction()
            navController.navigate("RegisterRoute")}) {

                Text("sí")
            }

        },
        dismissButton = {
            TextButton(onClick = {vm.hideDialog()
            navController.navigate("LoginRegRoute")}) {
                Text("no")
            }
        }
    )
}
