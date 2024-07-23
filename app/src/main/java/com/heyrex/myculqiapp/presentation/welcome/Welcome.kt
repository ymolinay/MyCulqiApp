package com.heyrex.myculqiapp.presentation.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.core.utils.isValidEmail
import com.heyrex.myculqiapp.presentation.NavigationItem
import com.heyrex.myculqiapp.presentation.common.composables.AppButton
import com.heyrex.myculqiapp.presentation.common.composables.AppDivider
import com.heyrex.myculqiapp.presentation.common.composables.AppLoader
import com.heyrex.myculqiapp.presentation.common.composables.AppTextField
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeBack
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeCard
import com.heyrex.myculqiapp.presentation.common.composables.EventProcessor
import com.heyrex.myculqiapp.presentation.common.composables.theme.textSimple
import com.heyrex.myculqiapp.presentation.common.composables.theme.titleBold

@Composable
fun Welcome(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navController: NavHostController,
    showError: (String) -> Unit = {}
) {
    val state = viewModel.viewState
    val scrollState = rememberScrollState()

    WelcomeContent(
        scrollState = scrollState,
        loading = state.loading,
        email = state.email,
        onEmailChanged = {
            viewModel.sendIntent(WelcomeIntent.EmailChanged(it))
        },
        onContinue = {
            viewModel.sendIntent(WelcomeIntent.DoContinue(state.email))
        },
        onSignUp = {
            viewModel.sendIntent(WelcomeIntent.DoSignUp(state.email))
        }
    )

    EventProcessor(viewModelEventDelegate = viewModel) { event ->
        when (event) {
            is WelcomeEvent.NavigateToLogin -> {
                navController.navigate(NavigationItem.Login.route.replace("{email}", event.email))
            }

            is WelcomeEvent.NavigateToSignUp -> {
                navController.navigate(NavigationItem.SignUp.route.replace("{email}", event.email))
            }

            is WelcomeEvent.ErrorKey -> {
                showError(stringResource(id = event.key))
            }

            else -> {}
        }
    }
}


@Composable
fun WelcomeContent(
    scrollState: ScrollState,
    loading: Boolean,
    email: String,
    onEmailChanged: (String) -> Unit,
    onContinue: () -> Unit,
    onSignUp: () -> Unit,
) {

    AnimatedVisibility(
        !loading,
        enter = fadeIn(initialAlpha = 0.3f),
        exit = fadeOut()
    ) {
        AppWelcomeBack {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                AppDivider(height = 180.dp)
                Text(
                    text = stringResource(id = R.string.welcome_title),
                    style = titleBold.copy(fontSize = 24.sp),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                AppWelcomeCard {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        AppTextField(
                            value = email,
                            placeholder = stringResource(id = R.string.welcome_email),
                            onValueChange = onEmailChanged
                        )
                        AppDivider(height = 12.dp)
                        AppButton(
                            text = stringResource(id = R.string.welcome_continue),
                            modifier = Modifier
                                .fillMaxWidth(),
                            enabled = email.isValidEmail(),
                            onClick = {
                                onContinue.invoke()
                            }
                        )
                        AppDivider(height = 4.dp)
                        Text(
                            text = stringResource(id = R.string.welcome_or),
                            style = titleBold.copy(fontSize = 14.sp),
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        AppDivider(height = 4.dp)
                        AppButton(
                            text = stringResource(id = R.string.welcome_continue_facebook),
                            backgroundColor = Color.White,
                            startIcon = Icons.Default.Facebook,
                            startIconColor = Color.Blue,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {}
                        )
                        AppDivider(height = 12.dp)
                        AppButton(
                            text = stringResource(id = R.string.welcome_continue_google),
                            backgroundColor = Color.White,
                            startIcon = Icons.Default.Camera,
                            startIconColor = Color.Red,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {}
                        )
                        AppDivider(height = 12.dp)
                        AppButton(
                            text = stringResource(id = R.string.welcome_continue_apple),
                            backgroundColor = Color.White,
                            startIcon = Icons.Default.AlternateEmail,
                            startIconColor = Color.Black,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {}
                        )
                        AppDivider(height = 12.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.welcome_dont_account),
                                style = textSimple.copy(fontSize = 16.sp),
                            )
                            VerticalDivider(
                                color = Color.Transparent,
                                thickness = 5.dp
                            )
                            Text(
                                text = stringResource(id = R.string.welcome_dont_account_signup),
                                style = titleBold.copy(fontSize = 16.sp),
                                color =
                                if (email.isValidEmail()) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .clickable {
                                        onSignUp.invoke()
                                    }
                            )
                        }
                        AppDivider(height = 12.dp)
                        Text(
                            text = stringResource(id = R.string.welcome_forgot_password),
                            style = titleBold.copy(fontSize = 16.sp),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        loading,
        enter = fadeIn(initialAlpha = 0.3f),
        exit = fadeOut()
    ) {
        AppLoader()
    }
}

@Preview
@Composable
fun WelcomePreview() {
    Welcome(navController = rememberNavController())
}

