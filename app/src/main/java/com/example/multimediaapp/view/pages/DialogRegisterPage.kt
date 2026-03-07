package com.example.multimediaapp.view.pages

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.viewmodel.vm.DialogVM

/**
 * @author Andrés
 */

//ejemplo dialog

@Composable
fun DialogRegisterScreen(
    navController: NavController,
    vm: DialogVM = viewModel(),

) {

    val uiState by vm.uiState.collectAsState()

    AlertDialog(
        onDismissRequest = {vm.hideDialog()},
        title = {
            Text("¿Confirmar registro?")
        },
        confirmButton = {

            TextButton(onClick = { vm.confirmAction()
            navController.navigate(ObjRoutes.INFOUSER)}) {

                Text("sí")
            }

        },
        dismissButton = {
            TextButton(onClick = {vm.hideDialog()
            navController.navigate(ObjRoutes.LOGINREG)}) {
                Text("no")
            }
        },

    )
}

@Preview(showBackground = true)
@Composable
fun DialogPrev() {

    DialogRegisterScreen(
        navController = rememberNavController(),

    )
}