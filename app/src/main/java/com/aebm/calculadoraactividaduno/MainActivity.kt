package com.aebm.calculadoraactividaduno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aebm.calculadoraactividaduno.ui.theme.CalculadoraActividadUnoTheme
import com.aebm.calculadoraactividaduno.ui.theme.FormularioScreen
import com.aebm.calculadoraactividaduno.ui.theme.ResultadoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraActividadUnoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavegacion(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavegacion(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "formulario",
        modifier = modifier
    ) {
        composable("formulario") {
            FormularioScreen(
                onCalcular = { bruto -> navController.navigate("resultado/$bruto") }
            )
        }
        composable("resultado/{bruto}") { backStackEntry ->
            val bruto = backStackEntry.arguments?.getString("bruto") ?: "0"
            ResultadoScreen(
                bruto = bruto,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}