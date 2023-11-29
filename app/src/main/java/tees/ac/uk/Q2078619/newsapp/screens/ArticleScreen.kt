package tees.ac.uk.Q2078619.newsapp.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ArticleScreen(url: String){
    AndroidView(factory = {context ->
        WebView(context).apply {
            setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    return false
                }
            })
            loadUrl(url)
        }
    } ){

    }
}