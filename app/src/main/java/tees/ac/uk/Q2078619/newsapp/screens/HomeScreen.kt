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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import tees.ac.uk.Q2078619.newsapp.R
import tees.ac.uk.Q2078619.newsapp.components.AppToolbar
import tees.ac.uk.Q2078619.newsapp.components.NavigationDrawerBody
import tees.ac.uk.Q2078619.newsapp.components.NavigationDrawerHeader
import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.viewmodels.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var context = LocalContext.current
    LaunchedEffect(Unit) {
        homeViewModel.getTopHeadlineNews("general")
        homeViewModel.getUserData()
        val toastMessage = homeViewModel.statusMsg
        Toast.makeText(context,toastMessage, Toast.LENGTH_SHORT).show()
    }


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
                onNavigationItemClicked = { navigationItem ->
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                    homeViewModel.getTopHeadlineNews(navigationItem.name)
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
            ImageHolder(imageUrl = article.image)
            Text(
                text = article.title,
                style = MaterialTheme.typography.h6,
                maxLines = 3,
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
fun ImageHolder(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        placeholder = painterResource(R.drawable.img),
        error = painterResource(R.drawable.img_1)
    )
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}