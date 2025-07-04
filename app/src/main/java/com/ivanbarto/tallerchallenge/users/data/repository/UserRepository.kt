package com.ivanbarto.tallerchallenge.users.data.repository

import com.ivanbarto.tallerchallenge.users.data.dto.UserDto

interface UserRepository {
    suspend fun getUser(): List<UserDto>
}