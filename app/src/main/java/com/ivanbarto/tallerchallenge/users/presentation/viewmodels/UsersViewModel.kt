package com.ivanbarto.tallerchallenge.users.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanbarto.tallerchallenge.users.domain.models.User
import com.ivanbarto.tallerchallenge.users.domain.interactors.UserInteractor
import com.ivanbarto.tallerchallenge.users.presentation.base.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

            _screenState.value = ScreenState.Loading

            val users = interactor.getUsersAlphabetically()

            _screenState.value = ScreenState.Idle(users)
        }
    }

}


