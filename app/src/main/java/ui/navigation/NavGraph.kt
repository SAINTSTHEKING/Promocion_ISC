package com.example.promocion_isc.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object PlanEstudios : Screen("plan_estudios")
    object CampoLaboral : Screen("campo_laboral")
    object Especialidades : Screen("especialidades")
    object Test : Screen("test")
}

