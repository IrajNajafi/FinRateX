package com.irajnajafi1988gmail.finratex.navigation

// Sealed class representing all app screens/routes
sealed class NavScreen(val route: String) {

    // Splash screen route
    data object SplashScreen : NavScreen("splash_screen")

    // Home screen route
    data object HomeScreen : NavScreen("home_screen")
}
