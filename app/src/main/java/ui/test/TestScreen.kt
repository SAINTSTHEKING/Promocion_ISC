package com.example.promocion_isc.ui.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.promocion_isc.data.Question


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(navController: NavController) {
    // Preguntas genéricas
    val questions = listOf(
        Question(1, "¿Te gusta resolver problemas lógicos y matemáticos?", listOf("Sí, mucho", "A veces", "No me gusta"), 0),
        Question(
            2,
            "¿Disfrutas aprender sobre nuevas tecnologías?",
            listOf("Siempre", "De vez en cuando", "Prefiero lo conocido"),
            0
        ),
        Question(3, "¿Te interesa cómo funcionan los programas y aplicaciones por dentro?", listOf("Me fascina", "Un poco", "No me interesa"), 0),
        Question(4, "¿Te sientes cómodo trabajando en equipo y comunicando ideas técnicas?", listOf("Totalmente", "Depende", "Prefiero trabajar solo"), 0),
        Question(5, "¿Te gusta la idea de crear soluciones innovadoras con tecnología?", listOf("Sí, es mi pasión", "Podría intentarlo", "No me veo haciendo eso"), 0)
    )

    var currentIndex by remember { mutableStateOf(0) }
    val selectedAnswers = remember { mutableStateMapOf<Int, Int>() } // questionId -> selectedOptionIndex
    var showResult by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Test Vocacional") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (showResult) {
            val score = selectedAnswers.count { (qId, selected) ->
                val question = questions.find { it.id == qId }
                question?.correctOptionIndex == selected
            }
            val total = questions.size
            ResultScreen(
                score = score,
                total = total,
                onRetry = {
                    selectedAnswers.clear()
                    currentIndex = 0
                    showResult = false
                },
                onBack = { navController.popBackStack() }
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                LinearProgressIndicator(
                    progress = (currentIndex + 1) / questions.size.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                val question = questions[currentIndex]
                Text(
                    "Pregunta ${currentIndex + 1} de ${questions.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    question.text,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(24.dp))
                question.options.forEachIndexed { index, option ->
                    val isSelected = selectedAnswers[question.id] == index
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                selectedAnswers[question.id] = index
                            },
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { selectedAnswers[question.id] = index }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(option)
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (currentIndex > 0) currentIndex--
                        },
                        enabled = currentIndex > 0
                    ) {
                        Text("Anterior")
                    }
                    Button(
                        onClick = {
                            if (currentIndex < questions.size - 1) {
                                if (selectedAnswers.containsKey(question.id)) {
                                    currentIndex++
                                }
                            } else {
                                if (selectedAnswers.size == questions.size) {
                                    showResult = true
                                }
                            }
                        },
                        enabled = selectedAnswers.containsKey(question.id)
                    ) {
                        Text(if (currentIndex == questions.size - 1) "Ver Resultado" else "Siguiente")
                    }
                }
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, total: Int, onRetry: () -> Unit, onBack: () -> Unit) {
    val percentage = (score * 100) / total
    val message = when {
        percentage >= 80 -> "¡ISC es perfecta para ti! Tienes el perfil ideal para destacar en esta carrera."
        percentage >= 50 -> "Tienes afinidad con ISC. Podría ser una excelente opción si te esfuerzas."
        else -> "Quizás ISC no sea tu primera opción, pero siempre puedes explorar más sobre tecnología."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Resultado",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "$score de $total respuestas correctas",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            "$percentage%",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                message,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f)
            ) {
                Text("Salir")
            }
            Button(
                onClick = onRetry,
                modifier = Modifier.weight(1f)
            ) {
                Text("Intentar de nuevo")
            }
        }
    }
}