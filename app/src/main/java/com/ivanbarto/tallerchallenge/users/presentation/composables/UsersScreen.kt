package com.ivanbarto.tallerchallenge.users.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ivanbarto.tallerchallenge.ui.theme.Typography
import com.ivanbarto.tallerchallenge.users.domain.models.User
import com.ivanbarto.tallerchallenge.users.presentation.base.ScreenState
import com.ivanbarto.tallerchallenge.users.presentation.viewmodels.UsersViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun UsersScreen() {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val viewmodel = koinViewModel<UsersViewModel>()

    val screenState = viewmodel.screenState.collectAsState().value

    LaunchedEffect(Unit) {
        viewmodel.getUsers()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                stickyHeader {
                    Text("Code Challenge", style = Typography.titleLarge)
                }

                when (screenState) {
                    is ScreenState.Idle<List<User>> -> {
                        items(screenState.data) { user ->
                            UserItem(user) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = user.toString(),
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    }

                    ScreenState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(
    user: User,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .clickable {
                onClick.invoke()
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        UserName(user)
        UserSurname(user)
    }
}

@Composable
fun UserName(user: User) {
    Text(text = user.name, style = Typography.titleMedium)
}

@Composable
fun UserSurname(user: User) {
    Text(text = user.surname, style = Typography.bodyLarge)
}