package com.example.promocion_isc.data

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctOptionIndex: Int // 0-based
)

data class Especialidad(
    val nombre: String,
    val descripcion: String,
    val iconRes: Int? = null // Puedes usar drawable o iconos de Material
)

data class PlanMateria(
    val semestre: Int,
    val materias: List<String>
)

