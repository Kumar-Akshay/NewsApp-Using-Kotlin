package tees.ac.uk.Q2078619.newsapp.viewmodels.homeViewModel

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
import tees.ac.uk.Q2078619.newsapp.services.RetrofitServiceInstance
import java.io.IOException
import java.util.Locale


class HomeViewModel : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    // Location lat and long
    var latitude = 0.0;
    var longitude = 0.0;
    var newsList = mutableStateOf<List<NewsArticle>>(listOf())
    val newsRepository = NewsRepositoryImpl(RetrofitServiceInstance.newsService)
    var loadingNewsInProgress = mutableStateOf(false)

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


    fun getCountryCode(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses != null && addresses.isNotEmpty())
        {
                return addresses[0].countryCode
        }
        else{
            return "gb"
        }
    }

    fun getTopHeadlineNews(category : String, country : String){
        loadingNewsInProgress.value = true;
        viewModelScope.launch {
            try
            {
                val response = newsRepository.getTopHeadlines(category,country)
                if (!response.data.isNullOrEmpty())
                {
                    loadingNewsInProgress.value = false;
                    newsList.value = response.data!!
                }
                else {
                    Log.d(TAG,"TopHeadline else")
                }
            } catch (e: Exception) {
                Log.d(TAG,"TopHeadline Exception")
            }
        }
    }
}