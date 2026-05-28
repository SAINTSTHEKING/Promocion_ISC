package com.example.promocion_isc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.promocion_isc.ui.details.CampoLaboralScreen
import com.example.promocion_isc.ui.details.EspecialidadesScreen
import com.example.promocion_isc.ui.details.PlanEstudiosScreen
import com.example.promocion_isc.ui.home.HomeScreen
import com.example.promocion_isc.ui.test.TestScreen
import com.example.promocion_isc.ui.splash.SplashScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.PlanEstudios.route) {
            PlanEstudiosScreen(navController)
        }
        composable(Screen.CampoLaboral.route) {
            CampoLaboralScreen(navController)
        }
        composable(Screen.Especialidades.route) {
            EspecialidadesScreen(navController)
        }
        composable(Screen.Test.route) {
            TestScreen(navController)
        }
    }
}
