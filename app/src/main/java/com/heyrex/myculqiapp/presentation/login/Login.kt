package com.heyrex.myculqiapp.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.core.utils.formatEmailUsername
import com.heyrex.myculqiapp.presentation.common.composables.AppButton
import com.heyrex.myculqiapp.presentation.common.composables.AppDivider
import com.heyrex.myculqiapp.presentation.common.composables.AppLoader
import com.heyrex.myculqiapp.presentation.common.composables.AppTextFieldPassword
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeBack
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeCard
import com.heyrex.myculqiapp.presentation.common.composables.CircularIcon
import com.heyrex.myculqiapp.presentation.common.composables.EventProcessor
import com.heyrex.myculqiapp.presentation.common.composables.theme.textSimple
import com.heyrex.myculqiapp.presentation.common.composables.theme.titleBold

@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController,
    email: String,
    onLoginSuccess: () -> Unit,
    showError: (String) -> Unit = {}
) {
    val state = viewModel.viewState
    LaunchedEffect(Unit) {
        viewModel.sendIntent(LoginIntent.EmailChanged(email))
    }

    LoginContent(
        loading = state.loading,
        email = state.email,
        password = state.password,
        onPasswordChanged = {
            viewModel.sendIntent(LoginIntent.PasswordChanged(it))
        },
        onLogin = {
            viewModel.sendIntent(
                LoginIntent.DoLogin(state.email, state.password)
            )
        }
    )

    EventProcessor(viewModelEventDelegate = viewModel) { event ->
        when (event) {
            is LoginEvent.NavigateToHome -> {
                onLoginSuccess.invoke()
            }

            is LoginEvent.ErrorKey -> {
                showError(stringResource(id = event.key))
            }

            else -> {}
        }
    }
}

@Composable
fun LoginContent(
    loading: Boolean,
    email: String,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onLogin: () -> Unit
) {

    AnimatedVisibility(
        !loading,
        enter = fadeIn(initialAlpha = 0.3f),
        exit = fadeOut()
    ) {
        AppWelcomeBack {
            Column(
                modifier = Modifier
                    .padding(top = 180.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.login_title),
                    style = titleBold.copy(fontSize = 24.sp),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                AppWelcomeCard {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularIcon(
                                imageVector = Icons.Default.Person,
                                backgroundColor = Color.LightGray,
                                iconColor = Color.White,
                                size = 60.dp
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = email.formatEmailUsername(),
                                    style = textSimple.copy(fontSize = 16.sp)
                                )
                                Text(
                                    text = email,
                                    style = titleBold.copy(fontSize = 16.sp)
                                )
                            }
                        }
                        AppDivider(height = 24.dp)
                        AppTextFieldPassword(
                            value = password,
                            placeholder = stringResource(id = R.string.login_password),
                            onValueChange = onPasswordChanged
                        )
                        AppDivider(height = 12.dp)
                        AppButton(
                            text = stringResource(id = R.string.welcome_continue),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { onLogin.invoke() }
                        )
                        AppDivider(height = 24.dp)
                        Text(
                            text = stringResource(id = R.string.welcome_forgot_password),
                            style = titleBold.copy(fontSize = 16.sp),
                            color = MaterialTheme.colorScheme.primary
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
fun LoginPreview() {
    Login(
        navController = rememberNavController(),
        email = "mary.smith@yopmail.com",
        onLoginSuccess = {})
}