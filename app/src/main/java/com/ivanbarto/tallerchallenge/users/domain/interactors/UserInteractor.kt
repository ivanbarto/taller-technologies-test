package com.ivanbarto.tallerchallenge.users.domain.interactors

import com.ivanbarto.tallerchallenge.users.domain.models.User

interface UserInteractor {
    suspend fun getUsersAlphabetically(): List<User>
}