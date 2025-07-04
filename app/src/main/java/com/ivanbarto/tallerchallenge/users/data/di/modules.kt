package com.ivanbarto.tallerchallenge.users.data.di

import com.ivanbarto.tallerchallenge.users.data.repository.UserRepository
import com.ivanbarto.tallerchallenge.users.data.repository.UserRepositoryImpl
import org.koin.dsl.module

private val modules = module {
    single<UserRepository> { UserRepositoryImpl() }
}

fun getDataModules() = modules