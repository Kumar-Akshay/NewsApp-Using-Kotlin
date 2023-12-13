package tees.ac.uk.Q2078619.newsapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import tees.ac.uk.Q2078619.newsapp.viewmodels.homeViewModel.HomeViewModel
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppRouter
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppScreen
import tees.ac.uk.Q2078619.newsapp.screens.HomeScreen
import tees.ac.uk.Q2078619.newsapp.screens.LoginScreen
import tees.ac.uk.Q2078619.newsapp.screens.TermsAndConditionsScreen
import tees.ac.uk.q2078619.newsapp.screens.RegistrationScreen

@Composable
fun NewsApp(homeViewModel: HomeViewModel) {

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

@Composable
fun SplashScreenWithDelay() {
    val lifecycleOwner = LocalLifecycleOwner.current
    var isLaunched by remember { mutableStateOf(false) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                isLaunched = true
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    // Start the LaunchedEffect outside of the observer
    if (isLaunched) {
        LaunchedEffect(Unit) {
            delay(3000) // 3-second delay
            NewsAppRouter.navigateTo(NewsAppScreen.LoginScreen)
        }
    }
    // Display the splash screen UI
}
