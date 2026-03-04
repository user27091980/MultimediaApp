package com.example.multimediaapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multimediaapp.navigation.NavGraph
import com.example.multimediaapp.navigation.ObjRoutes
import com.example.multimediaapp.ui.theme.MultimediaAppTheme
import com.example.multimediaapp.view.components.BottomBar
import com.example.multimediaapp.view.components.TopBar


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultimediaAppTheme {
                val navController = rememberNavController()
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry.value?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    //personalized topbar
                    topBar = {
                        if (currentRoute !in listOf(

                                ObjRoutes.LOGINREG,
                                ObjRoutes.REGISTER,
                                ObjRoutes.LOGIN
                            )
                        ) TopBar(navController)
                    },
                    //personalized bottombar
                    bottomBar = {
                        if (currentRoute in listOf(
                                ObjRoutes.MAIN,
                                ObjRoutes.BAND,
                                ObjRoutes.SETTINGS,
                                ObjRoutes.INFOUSER

                            )
                        ) {
                            BottomBar(navController)
                        }
                    },
                    //main content
                    content = { innerPadding ->
                        //box for wrap the content and apply padding
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            NavGraph(navController)
                        }
                    }
                )
            }
        }
    }
}


