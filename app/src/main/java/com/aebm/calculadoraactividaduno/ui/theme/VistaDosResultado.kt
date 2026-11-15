package com.aebm.calculadoraactividaduno.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResultadoScreen(
    bruto: String,
    pagas: String,
    hijos: String,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    val brutoNum = bruto.toDoubleOrNull() ?: 0.0
    val pagasNum = (pagas.toIntOrNull() ?: 14).coerceAtLeast(1)
    val hijosNum = hijos.toIntOrNull() ?: 0

    val porcentaje = when {
        brutoNum < 15000 -> 0.10
        brutoNum < 30000 -> 0.15
        brutoNum < 50000 -> 0.20
        else -> 0.25
    }
    val retencion = brutoNum * porcentaje
    val deducciones = hijosNum * 300.0
    val neto = brutoNum - retencion + deducciones
    val netoPorPaga = neto / pagasNum

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Salario bruto: ${"%.2f".format(brutoNum)} €")
        Text("Retención de IRPF: ${"%.2f".format(retencion)} €")
        Text("Posibles deducciones: ${"%.2f".format(deducciones)} €")
        Text("Salario neto: ${"%.2f".format(neto)} €")
        Text("Neto por paga ($pagasNum pagas): ${"%.2f".format(netoPorPaga)} €")

        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaDosPreview() {
    ResultadoScreen(bruto = "30000", pagas = "14", hijos = "2", onVolver = {})
}