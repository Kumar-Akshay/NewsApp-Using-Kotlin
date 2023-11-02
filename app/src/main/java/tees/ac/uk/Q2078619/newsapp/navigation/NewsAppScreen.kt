package tees.ac.uk.Q2078619.newsapp.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed  class NewsAppScreen {
    object SignUpScreen : NewsAppScreen()
    object TermsAndConditionsScreen : NewsAppScreen()
    object LoginScreen : NewsAppScreen()
    object HomeScreen : NewsAppScreen()
}

object NewsAppRouter {

    var currentScreen: MutableState<NewsAppScreen> = mutableStateOf(NewsAppScreen.SignUpScreen)

    fun navigateTo(destination : NewsAppScreen){
        currentScreen.value = destination
    }


}