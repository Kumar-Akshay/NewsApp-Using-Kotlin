package tees.ac.uk.Q2078619.newsapp.viewmodels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenewsroom.data.remote.repository.NewsRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.utils.NavigationItem
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppRouter
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppScreen
import tees.ac.uk.Q2078619.newsapp.services.NewsApiService
import tees.ac.uk.Q2078619.newsapp.services.RetrofitServiceInstance
import tees.ac.uk.q2078619.newsapp.data.login.LoginViewModel


class HomeViewModel : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName
    var statusMsg = "";
    var newsList = mutableStateOf<List<NewsArticle>>(listOf())
    val newsRepository = NewsRepositoryImpl(RetrofitServiceInstance.newsService)

    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Business News",
            icon = Icons.Default.Favorite,
            description = "Business News",
            name = "business"
        ),
        NavigationItem(
            title = "Technology News",
            icon = Icons.Default.Favorite,
            description = "Sport News Screen",
            name = "technology"
        ),
        NavigationItem(
            title = "Sport News",
            icon = Icons.Default.Favorite,
            description = "Sport News Screen",
            name = "sports"
        ),
        NavigationItem(
            title = "World News",
            icon = Icons.Default.Favorite,
            description = "World News Screen",
            name = "world"
        ),
        NavigationItem(
            title = "Entertainment News",
            icon = Icons.Default.Favorite,
            description = "Entertainment News Screen",
            name = "entertainment"
        ),
    )
    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                NewsAppRouter.navigateTo(NewsAppScreen.LoginScreen)
            } else {
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            isUserLoggedIn.value = true
        } else {
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


    fun getTopHeadlineNews(category : String){
        viewModelScope.launch {
            try
            {
                val response = newsRepository.getTopHeadlines(category)
                if (!response.data.isNullOrEmpty())
                {
                    newsList.value = response.data!!
                    statusMsg = "Recent News"
                }
                else {
                    Log.d(TAG,"TopHeadline else")
                    statusMsg = "Unable to get news, Try again"
                }
            } catch (e: Exception) {
                Log.d(TAG,"TopHeadline Exception")
                statusMsg = "Unable to get news, Try again"
            }
        }
    }
}