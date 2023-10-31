package tees.ac.uk.q2078619.newsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tees.ac.uk.Q2078619.newsapp.R
import tees.ac.uk.Q2078619.newsapp.components.HeadingTextComponent
import tees.ac.uk.Q2078619.newsapp.components.NormalTextComponent
import tees.ac.uk.Q2078619.newsapp.components.TextFieldComponent

@Composable

fun RegistrationScreen() {
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

                NormalTextComponent(value = stringResource(id = R.string.helloMsg))
                HeadingTextComponent(value = stringResource(id = R.string.create_accountMsg))
                TextFieldComponent(labelValue = stringResource(id = R.string.first_nameMsg))
                TextFieldComponent(labelValue = stringResource(id = R.string.last_nameMsg))
                TextFieldComponent(labelValue = stringResource(id = R.string.emailMsg))
                TextFieldComponent(labelValue = stringResource(id = R.string.passwordMsg))
            }
        }
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}