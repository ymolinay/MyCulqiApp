package com.heyrex.myculqiapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.heyrex.myculqiapp.presentation.home.Home
import com.heyrex.myculqiapp.presentation.login.Login
import com.heyrex.myculqiapp.presentation.signup.SignUp
import com.heyrex.myculqiapp.presentation.welcome.Welcome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    fun loginSuccess() {
        isLoggedIn = true
        navController.navigate(NavigationItem.Home.route) {
            popUpTo(NavigationItem.Login.route) { inclusive = true }
        }
    }

    val showError: (String) -> Unit = { message ->
        CoroutineScope(Dispatchers.Main).launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = if (isLoggedIn) NavigationItem.Home.route else NavigationItem.Welcome.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationItem.Welcome.route) {
                Welcome(navController = navController, showError = showError)
            }
            composable(NavigationItem.SignUp.route) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email")
                email?.let {
                    SignUp(
                        navController = navController,
                        email = it,
                        onLoginSuccess = { loginSuccess() },
                        showError = showError
                    )
                }
            }
            composable(NavigationItem.Login.route) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email")
                email?.let {
                    Login(
                        navController = navController,
                        email = it,
                        onLoginSuccess = { loginSuccess() },
                        showError = showError
                    )
                }
            }
            composable(NavigationItem.Home.route) {
                Home(navController = navController, showError = showError)
            }
        }
    }
}


enum class Screen {
    WELCOME,
    LOGIN,
    SIGNUP,
    HOME,
}

sealed class NavigationItem(val route: String) {
    object Welcome : NavigationItem(Screen.WELCOME.name)
    object Login : NavigationItem(Screen.LOGIN.name + "/{email}")
    object SignUp : NavigationItem(Screen.SIGNUP.name + "/{email}")
    object Home : NavigationItem(Screen.HOME.name)
}