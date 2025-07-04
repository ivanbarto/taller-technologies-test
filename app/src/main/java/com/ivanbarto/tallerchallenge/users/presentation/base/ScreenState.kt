package com.ivanbarto.tallerchallenge.users.presentation.base

sealed class ScreenState<out T>(open val message: String? = null) {

    data class Loading(override val message: String? = null) : ScreenState<Nothing>(message = message)

    data class Idle<out T>(val data: T, override val message: String? = null) :
        ScreenState<T>(message = message)
}