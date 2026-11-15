package com.aebm.calculadoraactividaduno.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
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
    var mostrarErrores by remember { mutableStateOf(false) }

    val pagasValidas = numPagas == "12" || numPagas == "14"
    val edadValida = (edad.toIntOrNull() ?: 0) >= 16

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = salarioBruto,
            onValueChange = { if (it.all { c -> c.isDigit() }) salarioBruto = it },
            label = { Text("Salario bruto anual") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = numPagas,
            onValueChange = { if (it.length <= 2 && it.all { c -> c.isDigit() }) numPagas = it },
            label = { Text("Número de pagas (12 o 14)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = mostrarErrores && !pagasValidas,
            supportingText = {
                if (mostrarErrores && !pagasValidas) Text("Debe ser 12 o 14")
            }
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { if (it.all { c -> c.isDigit() }) edad = it },
            label = { Text("Edad (mínimo 16)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = mostrarErrores && !edadValida,
            supportingText = {
                if (mostrarErrores && !edadValida) Text("Debe tener 16 años o más")
            }
        )
        OutlinedTextField(
            value = grupo,
            onValueChange = { if (it.all { c -> c.isDigit() }) grupo = it },
            label = { Text("Grupo profesional") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = discapacidad,
            onValueChange = { if (it.all { c -> c.isDigit() }) discapacidad = it },
            label = { Text("Grado de discapacidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = estadoCivil,
            onValueChange = { if (it.all { c -> c.isLetter() || c == ' ' }) estadoCivil = it },
            label = { Text("Estado civil") },
            singleLine = true
        )
        OutlinedTextField(
            value = numHijos,
            onValueChange = { if (it.all { c -> c.isDigit() }) numHijos = it },
            label = { Text("Número de hijos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Button(onClick = {
            mostrarErrores = true
            if (pagasValidas && edadValida) {
                onCalcular(
                    salarioBruto.ifEmpty { "0" },
                    numPagas,
                    numHijos.ifEmpty { "0" }
                )
            }
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