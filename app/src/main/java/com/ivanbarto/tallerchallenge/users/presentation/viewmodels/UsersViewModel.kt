package com.ivanbarto.tallerchallenge.users.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanbarto.tallerchallenge.users.domain.models.User
import com.ivanbarto.tallerchallenge.users.domain.interactors.UserInteractor
import com.ivanbarto.tallerchallenge.users.presentation.base.ScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(private val interactor: UserInteractor) : ViewModel() {

    private val _screenState: MutableStateFlow<ScreenState<List<User>>> = MutableStateFlow(
        ScreenState.Idle(
            emptyList()
        )
    )

    val screenState: StateFlow<ScreenState<List<User>>> = _screenState.asStateFlow()


    fun getUsers() {
        viewModelScope.launch {

            _screenState.update {
                ScreenState.Loading()
            }

            val users = interactor.getUsersAlphabetically()

            _screenState.update {
                ScreenState.Idle(users)
            }
        }
    }

    fun onUserClick(user: User) {
        viewModelScope.launch {
            _screenState.update { state ->
                when (state) {
                    is ScreenState.Idle -> state.copy(message = user.toString())
                    is ScreenState.Loading -> state.copy(message = user.toString())
                }
            }
            delay(1000)
            _screenState.update { state ->
                when (state) {
                    is ScreenState.Idle -> state.copy(message = null)
                    is ScreenState.Loading -> state.copy(message = null)
                }
            }
        }
    }
}


