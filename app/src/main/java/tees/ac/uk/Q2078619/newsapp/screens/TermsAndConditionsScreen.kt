package tees.ac.uk.Q2078619.newsapp.screens

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tees.ac.uk.Q2078619.newsapp.R
import tees.ac.uk.Q2078619.newsapp.components.HeadingTextComponent
import tees.ac.uk.Q2078619.newsapp.components.NormalTextComponent
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppRouter
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppScreen
import tees.ac.uk.Q2078619.newsapp.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                HeadingTextComponent(value = stringResource(id = R.string.Terms_and_Conditions))
                Spacer(modifier = Modifier.height(40.dp))
                NormalTextComponent(value = stringResource(id = R.string.Terms_and_Conditions_Text))
            }
        }
        SystemBackButtonHandler {
            NewsAppRouter.navigateTo(NewsAppScreen.SignUpScreen)
        }
    }
}
@Preview
@Composable
fun TermsAndConditionsScreenPreview(){
    TermsAndConditionsScreen()
}