package com.ivanbarto.tallerchallenge.users.presentation.base

sealed class ScreenState<out T> {
    data object Loading : ScreenState<Nothing>()
    data class Idle<out T>(val data: T) : ScreenState<T>()
}