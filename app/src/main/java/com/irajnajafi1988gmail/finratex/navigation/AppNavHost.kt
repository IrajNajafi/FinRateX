package com.irajnajafi1988gmail.finratex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.irajnajafi1988gmail.finratex.ui.components.splash.SplashScreen
import com.irajnajafi1988gmail.finratex.ui.screens.homeScreen.HomeScreen

// Main app navigation graph
@Composable
fun AppNavHost() {
    // Create NavController to manage navigation between screens
    val navController = rememberNavController()

    // Define navigation host and routes
    NavHost(
        navController = navController,
        startDestination = NavScreen.SplashScreen.route
    ) {
        // Splash screen route
        composable(route = NavScreen.SplashScreen.route) {
            SplashScreen(
                // Navigate to HomeScreen after splash
                navigateToHomeScreen = {
                    navController.navigate(NavScreen.HomeScreen.route) {
                        // Remove SplashScreen from back stack
                        popUpTo(NavScreen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Home screen route
        composable(route = NavScreen.HomeScreen.route) {
            HomeScreen()
        }
    }
}
