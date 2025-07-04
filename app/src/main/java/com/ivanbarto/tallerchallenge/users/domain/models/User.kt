package com.ivanbarto.tallerchallenge.users.domain.models

import com.ivanbarto.tallerchallenge.users.data.dto.UserDto

data class User(
    val name: String,
    val surname: String
){
    override fun toString(): String {
        return "I'm $name $surname"
    }
}

fun UserDto.toDomain(): User = User(
    name = name.orEmpty(),
    surname = surname.orEmpty(),
)
