package tees.ac.uk.Q2078619.newsapp.services

import android.app.Application
import com.google.firebase.FirebaseApp

class NewsAppFirebase : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}