package com.ivanbarto.tallerchallenge.users.presentation.di

import com.ivanbarto.tallerchallenge.users.domain.di.getDomainModules
import com.ivanbarto.tallerchallenge.users.presentation.viewmodels.UsersViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val modules = module {
    includes(getDomainModules())
    viewModelOf(::UsersViewModel)
}

fun getPresentationModules() = modules