package tees.ac.uk.Q2078619.newsapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import tees.ac.uk.Q2078619.newsapp.R
import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.viewmodels.HomeViewModel
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