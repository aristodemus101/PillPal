package com.example.pillpal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pillpal.ui.fragments.AddMedicineScreen
import com.example.pillpal.ui.fragments.DetailScreen
import com.example.pillpal.ui.fragments.HomeScreen
import com.example.pillpal.ui.fragments.SplashScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SPLASH.name) {
        // Splash Screen
        composable(route = Screen.SPLASH.name) {
            SplashScreen(navController = navController)
        }
        // Home Screen
        composable(route = Screen.HOME.name) {
            HomeScreen(navController = navController)
        }
        // Detail Screen
        composable(
            route = Screen.DETAIL.name,
        ) {
            DetailScreen()
        }
        // Add Pill Screen
        composable(
            route = Screen.ADD_PILL.name,
        ) {
            AddMedicineScreen(navController = navController)
        }
    }
}