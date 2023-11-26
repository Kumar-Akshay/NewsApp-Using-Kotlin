package tees.ac.uk.Q2078619.newsapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import tees.ac.uk.Q2078619.newsapp.viewmodels.homeViewModel.HomeViewModel
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppRouter
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppScreen
import tees.ac.uk.Q2078619.newsapp.screens.HomeScreen
import tees.ac.uk.Q2078619.newsapp.screens.LoginScreen
import tees.ac.uk.Q2078619.newsapp.screens.TermsAndConditionsScreen
import tees.ac.uk.q2078619.newsapp.screens.RegistrationScreen

@Composable
fun NewsApp(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            NewsAppRouter.navigateTo(NewsAppScreen.HomeScreen)
        }

        Crossfade(targetState = NewsAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is NewsAppScreen.SignUpScreen -> {
                    RegistrationScreen()
                }
                is NewsAppScreen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is NewsAppScreen.LoginScreen -> {
                    LoginScreen()
                }

                is NewsAppScreen.HomeScreen -> {
                    HomeScreen()
                }
            }
        }

    }
}