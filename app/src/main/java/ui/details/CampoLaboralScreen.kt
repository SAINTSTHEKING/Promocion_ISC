package com.example.promocion_isc.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.promocion_isc.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoLaboralScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Campo Laboral") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                "¿Dónde trabajan nuestros egresados?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Aquí puedes listar empresas con iconos o imágenes
            EmpresaItem("Google", "Desarrollo de software, IA, Cloud")
            EmpresaItem("Microsoft", "Servicios cloud, productividad, sistemas")
            EmpresaItem("Startups tecnológicas", "Emprendimiento, desarrollo ágil")
            EmpresaItem("Consultoras (Accenture, Deloitte)", "Transformación digital")
            EmpresaItem("Bancos y Fintech", "Seguridad, big data")
        }
    }
}

@Composable
fun EmpresaItem(nombre: String, descripcion: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono genérico usando el logo del ISC como ejemplo
            Icon(
                painter = painterResource(id = R.drawable.isc_logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(nombre, fontWeight = FontWeight.SemiBold)
                Text(descripcion, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
