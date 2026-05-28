package com.example.promocion_isc.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.promocion_isc.data.PlanMateria


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanEstudiosScreen(navController: NavController) {
    // Datos de ejemplo (puedes cargarlos desde un ViewModel)
    val plan = listOf(
        PlanMateria(
            1,
            listOf("Calculo Diferencial" , "Fundamentos de Programación ", "Taller de Etica ", "Matematicas Discretas ", "Taller de Administracion ", "Fundamentos de Investigacion " )
        ),
        PlanMateria(2, listOf("Programación Orientada a Objetos", "Calculo Integral", "Álgebra Lineal", "Contabilidad Financiera", "Probabilidad y Estadística", "Química")),
        PlanMateria(3, listOf("Calculo Vectorial", "Estructura de Datos", "Cultura Empresarial", "Investigación de Operaciones", "Desarrollo Sustentable", "Fisica General")),
        PlanMateria(4, listOf("Ecuaciones Diferenciales", "Metodos Numericos", "Topicos Avanzados de Programacion", "Fundamentos de Base de Datos", "Simulacion", "Principios Electricos y Aplicaciones Digitales")),
        PlanMateria(5, listOf("Graficacion", "Fundamentos de Telecomunicaciones", "Sistemas Operativos", "Taller de Bases de Dastos", "Fundamentos de Ingenieria en Software", "Arquitectura de Computadoras", "Negocioas Electronicos")),
        PlanMateria(6, listOf("Lenguajes y automatas", "Redes y Computadoras", "Taller de Sitsemas Oparativos" , "Administracion de Bases de Datos", "Ingenieria de Software", "Lenguajes de Interfaz", "Taller de Investigacion 1")),
        PlanMateria(7, listOf("Lenguajes y Automatas 2", "Conmutacion y enrutamiento de Redes de Datos", "Taller de Investigacion 2", "Programacion Web", "Gestion de Proyectos de Software", "Sistemas Programables", "Administracion de Redes")),
        PlanMateria(8, listOf("Programacion Logica y Funcional", "Inteligencia Artificial", "Interaccion Humano Computadora", "Lenguajes Web", "Ingernieria Web", "Topicos Selectos de Aplicaciones Moviles", "Verificacion y Validacion de Software")),
        PlanMateria(9, listOf("Residencias Profesionales"))
        // ... añade más semestres
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Plan de Estudios") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(plan) { semestre ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Semestre ${semestre.semestre}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        semestre.materias.forEach { materia ->
                            Text("• $materia", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}