package tees.ac.uk.Q2078619.newsapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import tees.ac.uk.Q2078619.newsapp.R
import tees.ac.uk.Q2078619.newsapp.app.ImageHolder
import tees.ac.uk.Q2078619.newsapp.app.NewsArticleCard
import tees.ac.uk.Q2078619.newsapp.components.AppToolbar
import tees.ac.uk.Q2078619.newsapp.components.NavigationDrawerBody
import tees.ac.uk.Q2078619.newsapp.components.NavigationDrawerHeader
import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.viewmodels.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    homeViewModel.getUserData()
    homeViewModel.getTopHeadlineNews("technology")

    val toastMessage = homeViewModel.statusMsg

    Toast.makeText(LocalContext.current,toastMessage, Toast.LENGTH_SHORT).show()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(toolbarTitle = stringResource(id = R.string.home),
                logoutButtonClicked = {
                    homeViewModel.logout()
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavigationDrawerHeader(homeViewModel.emailId.value)
            NavigationDrawerBody(navigationDrawerItems = homeViewModel.navigationItemsList,
                onNavigationItemClicked = {

                })
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                NewsArticlesList(homeViewModel.newsList.value, onCardClicked = {},)
            }
        }
    }
}

@Composable
fun NewsArticlesList(
    articles: List<NewsArticle>,
    onCardClicked : (NewsArticle) -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(articles) { article ->
            NewsArticleCard(
                article = article,
                onCardClicked = onCardClicked
            )
        }
    }
}

@Composable
fun NewsArticleCard(
    modifier: Modifier = Modifier,
    article: NewsArticle,
    onCardClicked: (NewsArticle) -> Unit
){
    Card(
        modifier = Modifier.clickable { onCardClicked(article) }
    ) {
        Column(  modifier = Modifier.padding(12.dp)) {
            ImageHolder(imageUrl = article.urlToImage)
            Text(
                text = article.title,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.body2,
                )
                Text(
                    text = article.publishedAt ?: "",
                    style = MaterialTheme.typography.body2,
                )

            }
        }
    }

}



@Composable
fun NewsCard(newsArticle: NewsArticle) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = newsArticle.title, style = MaterialTheme.typography.h6)
            Text(text = newsArticle.publishedAt.toString(), style = MaterialTheme.typography.body2)
            // Add other news details you wish to display
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}