package com.example.promocion_isc.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.promocion_isc.R
import com.example.promocion_isc.ui.components.ContactFloatingButton
import com.example.promocion_isc.ui.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButton = { ContactFloatingButton() },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "ISC - Infografía Interactiva",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sección ¿Qué aprenderás?
            ExpandableSectionCard(
                title = "¿Qué aprenderás?",
                icon = Icons.Default.School,
                initiallyExpanded = true,
                content = {
                    Text("Domina programación, inteligencia artificial, ciberseguridad, redes y desarrollo de software con tecnologías de vanguardia.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { navController.navigate(Screen.PlanEstudios.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ver Plan de Estudios")
                    }
                }
            )

            // Sección Campo Laboral
            ExpandableSectionCard(
                title = "Campo Laboral",
                icon = Icons.Default.Work,
                initiallyExpanded = false,
                content = {
                    Text("Trabaja en empresas líderes como Google, Microsoft, startups innovadoras, consultoría tecnológica o emprende tu propio negocio.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { navController.navigate(Screen.CampoLaboral.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Explorar Oportunidades")
                    }
                }
            )

            // Sección Mapa de Especialidades
            ExpandableSectionCard(
                title = "Especialidades",
                icon = Icons.Default.Map,
                initiallyExpanded = false,
                content = {
                    Text("Inteligencia Artificial, Ciberseguridad, Desarrollo Móvil, Cloud Computing, Ciencia de Datos y más.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { navController.navigate(Screen.Especialidades.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ver Especialidades")
                    }
                }
            )

            // Sección Test Rápido
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.Test.route) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Quiz,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "¿Es Sistemas para ti?",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Realiza este test rápido de 5 preguntas",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Sección QR (descarga PDF)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Escanea el QR para descargar el Plan de Estudios (PDF)",
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.qr_code),
                        contentDescription = "QR Plan de Estudios",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ExpandableSectionCard(
    title: String,
    icon: ImageVector,
    initiallyExpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Colapsar" else "Expandir",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = tween(300)),
                exit = shrinkVertically(animationSpec = tween(300))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    content()
                }
            }
        }
    }
}
