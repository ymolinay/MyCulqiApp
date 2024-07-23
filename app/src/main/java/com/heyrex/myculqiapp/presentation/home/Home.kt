package com.heyrex.myculqiapp.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.heyrex.myculqiapp.domain.model.Friend
import com.heyrex.myculqiapp.domain.model.Profile
import com.heyrex.myculqiapp.presentation.common.composables.AppDivider
import com.heyrex.myculqiapp.presentation.common.composables.AppLoader
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeBack
import com.heyrex.myculqiapp.presentation.common.composables.EventProcessor
import com.heyrex.myculqiapp.presentation.common.composables.theme.titleBold
import com.heyrex.myculqiapp.presentation.home.components.AvatarCardItem

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    showError: (String) -> Unit = {}
) {
    val state = viewModel.viewState
    LaunchedEffect(Unit) {
        viewModel.sendIntent(HomeIntent.GetProfile("4"))
    }

    HomeContent(
        loading = state.loading,
        profile = state.profile,
        friends = state.friends
    )

    EventProcessor(viewModelEventDelegate = viewModel) { event ->
        when (event) {
            is HomeEvent.GetFriends -> {
                viewModel.sendIntent(HomeIntent.GetFriends)
            }

            is HomeEvent.ErrorKey -> {
                showError(stringResource(id = event.key))
            }

            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    loading: Boolean,
    profile: Profile?,
    friends: List<Friend>?,
) {

    AnimatedVisibility(
        !loading,
        enter = fadeIn(initialAlpha = 0.3f),
        exit = fadeOut()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = stringResource(id = R.string.home_title))
                })
            }
        ) { innerPadding ->
            AppWelcomeBack {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            start = 16.dp,
                            end = 16.dp
                        ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        AppDivider(height = 20.dp)
                        Text(
                            text = stringResource(id = R.string.welcome_title),
                            style = titleBold.copy(color = Color.White, fontSize = 24.sp),
                            modifier = Modifier
                                .padding(start = 12.dp)
                        )
                    }
                    profile?.let {
                        item {
                            AvatarCardItem(
                                name = it.name,
                                email = it.email,
                                image = it.avatar
                            )
                        }
                    }
                    items(friends.orEmpty()) { item ->
                        if (item == friends?.first()) {
                            AppDivider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                height = 8.dp
                            )
                            Text(
                                text = stringResource(id = R.string.home_friends),
                                style = titleBold.copy(color = Color.White, fontSize = 24.sp),
                                modifier = Modifier
                                    .padding(start = 12.dp)
                            )
                        }
                        AvatarCardItem(
                            name = item.name,
                            email = item.email,
                            image = item.avatar
                        )
                        if (item != friends?.last()) {
                            AppDivider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                height = 2.dp
                            )
                        }
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
fun HomePreview() {
    Home(navController = rememberNavController())
}