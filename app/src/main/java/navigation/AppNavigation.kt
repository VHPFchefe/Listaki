package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.home.domain.event.HomeEvent
import presentation.home.domain.event.HomeState
import presentation.login.screen.LoginScreen
import presentation.home.screens.HomeScreen

@Composable
fun AppNavigation(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(Screen.Login.route){
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = Screen.Home.route) {
            HomeScreen(state, onEvent)
        }
    }
}

