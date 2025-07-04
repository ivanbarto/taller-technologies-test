package com.ivanbarto.tallerchallenge.users.data.repository

import com.ivanbarto.tallerchallenge.users.data.dto.UserDto
import kotlinx.coroutines.delay

class UserRepositoryImpl: UserRepository {
    override suspend fun getUser(): List<UserDto> {
        //simulate network call
        delay(3000)

        return listOf(
            UserDto("User4","surname4"),
            UserDto("User1","surname1"),
            UserDto("User5","surname5"),
            UserDto("User2","surname2"),
            UserDto("User3","surname3"),
            UserDto("User6","surname6")
        )
    }
}