package tees.ac.uk.Q2078619.newsapp.viewmodels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.utils.NavigationItem
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppRouter
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppScreen
import tees.ac.uk.Q2078619.newsapp.services.RetrofitServiceInstance
import tees.ac.uk.q2078619.newsapp.data.login.LoginViewModel


class HomeViewModel : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName
    var newsList = mutableStateOf<List<NewsArticle>>(listOf())
    private val newsService = RetrofitServiceInstance.newsService

    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Top News HeadLine",
            icon = Icons.Default.Favorite,
            description = "Top Headline News Screen",
            itemId = "newsHeadlineScreen"
        ),
        NavigationItem(
            title = "Sport News",
            icon = Icons.Default.Favorite,
            description = "Sport News Screen",
            itemId = "sportNewsScreen"
        ),

        NavigationItem(
        title = "Technology News",
        icon = Icons.Default.Favorite,
        description = "Sport News Screen",
        itemId = "sportNewsScreen"
    )
    )
    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
                NewsAppRouter.navigateTo(NewsAppScreen.LoginScreen)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }
    val emailId: MutableLiveData<String> = MutableLiveData()
    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }


    fun getTopHeadlineNews(){
        viewModelScope.launch {
            try {
                val response = newsService.TopHeadlineNews("technology")
                if (response.status == "ok" && !response.articles.isEmpty()) {
                    newsList.value = response.articles!!
                    Log.d(TAG,"Inside_TopHeadLineNews")
                    Log.d(TAG,"count +"+newsList.value.count())
                } else {
                    // Handle errors here
                    Log.d(TAG,"Inside_Error")
                }
            } catch (e: Exception) {
                Log.d(TAG,"Inside_Exception")
                // Handle network or other errors here
            }
        }
    }
}