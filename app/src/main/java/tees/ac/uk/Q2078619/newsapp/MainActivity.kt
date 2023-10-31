package tees.ac.uk.q2078619.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tees.ac.uk.Q2078619.newsapp.app.NewsApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApp()
            }
        }
    }

@Preview
@Composable
fun DefaultPreview(){
    NewsApp()
}