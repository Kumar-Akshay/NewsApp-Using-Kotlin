package tees.ac.uk.q2078619.newsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tees.ac.uk.Q2078619.newsapp.R
import tees.ac.uk.Q2078619.newsapp.components.ButtonComponent
import tees.ac.uk.Q2078619.newsapp.components.CheckboxComponent
import tees.ac.uk.Q2078619.newsapp.components.ClickableLoginTextComponent
import tees.ac.uk.Q2078619.newsapp.components.DividerTextComponent
import tees.ac.uk.Q2078619.newsapp.components.HeadingTextComponent
import tees.ac.uk.Q2078619.newsapp.components.TextFieldComponent
import tees.ac.uk.Q2078619.newsapp.components.NormalTextComponent
import tees.ac.uk.Q2078619.newsapp.components.PasswordTextFieldComponent
import tees.ac.uk.Q2078619.newsapp.viewmodels.signup.RegistrationUIEvent
import tees.ac.uk.Q2078619.newsapp.viewmodels.signup.RegistrationViewModel
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppRouter
import tees.ac.uk.Q2078619.newsapp.navigation.NewsAppScreen

@Composable

fun RegistrationScreen(registrationViewModel: RegistrationViewModel = viewModel()) {
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
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.first_nameMsg),
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegistrationUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationUIState.value.firstNameError
                )
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.last_nameMsg),
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegistrationUIEvent.LastNameChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationUIState.value.lastNameError
                )

                TextFieldComponent(
                    labelValue = stringResource(id = R.string.emailMsg),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegistrationUIEvent.EmailChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.passwordMsg),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        registrationViewModel.onEvent(RegistrationUIEvent.PasswordChanged(it))
                    },
                    errorStatus = registrationViewModel.registrationUIState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        NewsAppRouter.navigateTo(NewsAppScreen.TermsAndConditionsScreen)
                    },
                    onCheckedChange = {
                        registrationViewModel.onEvent(RegistrationUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        registrationViewModel.onEvent(RegistrationUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = registrationViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    NewsAppRouter.navigateTo(NewsAppScreen.LoginScreen)
                })
            }

        }

        if(registrationViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }
}


@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}