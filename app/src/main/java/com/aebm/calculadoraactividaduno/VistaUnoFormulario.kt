package com.aebm.calculadoraactividaduno.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de salario neto",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = salarioBruto,
            onValueChange = { if (it.all { c -> c.isDigit() }) salarioBruto = it },
            label = { Text("Salario bruto anual") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
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
            },
            modifier = Modifier.fillMaxWidth()
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
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = grupo,
            onValueChange = { if (it.all { c -> c.isDigit() }) grupo = it },
            label = { Text("Grupo profesional") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = discapacidad,
            onValueChange = { if (it.all { c -> c.isDigit() }) discapacidad = it },
            label = { Text("Grado de discapacidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estadoCivil,
            onValueChange = { if (it.all { c -> c.isLetter() || c == ' ' }) estadoCivil = it },
            label = { Text("Estado civil") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = numHijos,
            onValueChange = { if (it.all { c -> c.isDigit() }) numHijos = it },
            label = { Text("Número de hijos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                mostrarErrores = true
                if (pagasValidas && edadValida) {
                    onCalcular(
                        salarioBruto.ifEmpty { "0" },
                        numPagas,
                        numHijos.ifEmpty { "0" }
                    )
                }
            },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AzulPrincipal,
                contentColor = androidx.compose.ui.graphics.Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text("Calcular", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioPreview() {
    FormularioScreen(onCalcular = { _, _, _ -> })
}