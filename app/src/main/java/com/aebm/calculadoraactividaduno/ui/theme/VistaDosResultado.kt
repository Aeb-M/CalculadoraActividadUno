package com.aebm.calculadoraactividaduno.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResultadoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Salario bruto: 0")
        Text("Salario neto: 0")
        Text("Retención de IRPF: 0")
        Text("Posibles deducciones: 0")

        Button(onClick = { }) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaDosPreview() {
    ResultadoScreen()
}