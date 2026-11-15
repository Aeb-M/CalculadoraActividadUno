package com.aebm.calculadoraactividaduno.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FormularioScreen(onCalcular: (String, String, String) -> Unit, modifier: Modifier = Modifier) {
    var salarioBruto by remember { mutableStateOf("") }
    var numPagas by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var grupo by remember { mutableStateOf("") }
    var discapacidad by remember { mutableStateOf("") }
    var estadoCivil by remember { mutableStateOf("") }
    var numHijos by remember { mutableStateOf("") }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(value = salarioBruto, onValueChange = { salarioBruto = it }, label = { Text("Salario bruto anual") })
        OutlinedTextField(value = numPagas, onValueChange = { numPagas = it }, label = { Text("Número de pagas") })
        OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
        OutlinedTextField(value = grupo, onValueChange = { grupo = it }, label = { Text("Grupo profesional") })
        OutlinedTextField(value = discapacidad, onValueChange = { discapacidad = it }, label = { Text("Grado de discapacidad") })
        OutlinedTextField(value = estadoCivil, onValueChange = { estadoCivil = it }, label = { Text("Estado civil") })
        OutlinedTextField(value = numHijos, onValueChange = { numHijos = it }, label = { Text("Número de hijos") })

        Button(onClick = {
            onCalcular(
                salarioBruto.ifEmpty { "0" },
                numPagas.ifEmpty { "14" },
                numHijos.ifEmpty { "0" }
            )
        }) {
            Text("Calcular")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioPreview() {
    FormularioScreen(onCalcular = { _, _, _ -> })
}