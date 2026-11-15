package com.aebm.calculadoraactividaduno.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resultados",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = AzulClaro,
                contentColor = Color(0xFF1A1A1A)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Salario bruto: ${"%.2f".format(brutoNum)} €", fontSize = 18.sp)
                Text("Retención de IRPF: ${"%.2f".format(retencion)} €", fontSize = 18.sp)
                Text("Posibles deducciones: ${"%.2f".format(deducciones)} €", fontSize = 18.sp)
                Text(
                    text = "Salario neto: ${"%.2f".format(neto)} €",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulOscuro
                )
                Text("Neto por paga ($pagasNum pagas): ${"%.2f".format(netoPorPaga)} €", fontSize = 18.sp)
            }
        }

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AzulPrincipal,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text("Volver", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaDosPreview() {
    ResultadoScreen(bruto = "30000", pagas = "14", hijos = "2", onVolver = {})
}