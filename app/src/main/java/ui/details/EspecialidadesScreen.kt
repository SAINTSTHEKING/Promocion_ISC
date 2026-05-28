package com.example.promocion_isc.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.promocion_isc.data.Especialidad


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EspecialidadesScreen(navController: NavController) {
    val especialidades = listOf(
        Especialidad("Inteligencia Artificial", "Machine Learning, Deep Learning, NLP"),
        Especialidad("Ciberseguridad", "Protección de datos, redes seguras, hacking ético"),
        Especialidad("Desarrollo Móvil", "Android, iOS, aplicaciones híbridas"),
        Especialidad("Cloud Computing", "AWS, Azure, Google Cloud"),
        Especialidad("Ciencia de Datos", "Big Data, análisis predictivo"),
        Especialidad("Redes y Telecomunicaciones", "Infraestructura, IoT")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Especialidades") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(especialidades) { esp ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            esp.nombre,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            esp.descripcion,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 3
                        )
                    }
                }
            }
        }
    }
}
