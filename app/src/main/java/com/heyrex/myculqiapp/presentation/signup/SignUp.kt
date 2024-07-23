package com.heyrex.myculqiapp.presentation.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.heyrex.myculqiapp.presentation.common.composables.AppButton
import com.heyrex.myculqiapp.presentation.common.composables.AppDivider
import com.heyrex.myculqiapp.presentation.common.composables.AppLoader
import com.heyrex.myculqiapp.presentation.common.composables.AppTextField
import com.heyrex.myculqiapp.presentation.common.composables.AppTextFieldPassword
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeBack
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeCard
import com.heyrex.myculqiapp.presentation.common.composables.EventProcessor
import com.heyrex.myculqiapp.presentation.common.composables.theme.textSimple
import com.heyrex.myculqiapp.presentation.common.composables.theme.titleBold
import com.heyrex.myculqiapp.presentation.login.LoginEvent
import com.heyrex.myculqiapp.presentation.login.LoginIntent

@Composable
fun SignUp(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController,
    email: String,
    onLoginSuccess: () -> Unit,
    showError: (String) -> Unit = {}
) {
    val state = viewModel.viewState
    LaunchedEffect(Unit) {
        viewModel.sendIntent(SignUpIntent.EmailChanged(email))
    }

    SignUpContent(
        loading = state.loading,
        email = state.email,
        name = state.name,
        password = state.password,
        onNameChanged = {
            viewModel.sendIntent(SignUpIntent.NameChanged(it))
        },
        onPasswordChanged = {
            viewModel.sendIntent(SignUpIntent.PasswordChanged(it))
        },
        onLogin = {
            viewModel.sendIntent(
                SignUpIntent.DoSignUp(state.email, state.name, state.password)
            )
        }
    )

    EventProcessor(viewModelEventDelegate = viewModel) { event ->
        when (event) {
            is SignUpEvent.NavigateToHome -> {
                onLoginSuccess.invoke()
            }

            is SignUpEvent.ErrorKey -> {
                showError(stringResource(id = event.key))
            }

            else -> {}
        }
    }
}

@Composable
fun SignUpContent(
    loading: Boolean,
    email: String,
    name: String,
    password: String,
    onNameChanged: (String) -> Unit,
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
                        Text(
                            text = stringResource(id = R.string.signup_subtitle),
                            style = textSimple.copy(fontSize = 16.sp),
                        )
                        Text(
                            text = email,
                            style = textSimple.copy(fontSize = 16.sp),
                        )
                        AppDivider(height = 24.dp)
                        AppTextField(
                            value = name,
                            placeholder = stringResource(id = R.string.signup_name),
                            onValueChange = onNameChanged
                        )
                        AppDivider(height = 12.dp)
                        AppTextFieldPassword(
                            value = password,
                            placeholder = stringResource(id = R.string.login_password),
                            onValueChange = onPasswordChanged
                        )
                        AppDivider(height = 24.dp)
                        Text(
                            text = stringResource(id = R.string.signup_terms),
                            style = textSimple.copy(fontSize = 16.sp),
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.signup_terms_agree),
                                style = titleBold.copy(fontSize = 16.sp),
                            )
                            VerticalDivider(
                                color = Color.Transparent,
                                thickness = 5.dp
                            )
                            Text(
                                text = stringResource(id = R.string.signup_terms_agree_terms),
                                style = titleBold.copy(fontSize = 16.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        AppDivider(height = 24.dp)
                        AppButton(
                            text = stringResource(id = R.string.signup_terms_agree_continue),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { onLogin.invoke() }
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
fun SignUpPreview() {
    SignUp(
        navController = rememberNavController(),
        email = "mary.smith@yopmail.com",
        onLoginSuccess = {})
}