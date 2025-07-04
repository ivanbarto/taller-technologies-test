package com.ivanbarto.tallerchallenge.users.domain.interactors

import com.ivanbarto.tallerchallenge.users.data.repository.UserRepository
import com.ivanbarto.tallerchallenge.users.domain.models.User
import com.ivanbarto.tallerchallenge.users.domain.models.toDomain

class UserInteractorImpl(private val userRepository: UserRepository) : UserInteractor {
    override suspend fun getUsersAlphabetically(): List<User> =
        userRepository.getUser().map { it.toDomain() }.sortedBy { user -> user.name + user.surname }
}