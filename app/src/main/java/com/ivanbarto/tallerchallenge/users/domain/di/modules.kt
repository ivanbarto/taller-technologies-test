package com.ivanbarto.tallerchallenge.users.domain.di

import com.ivanbarto.tallerchallenge.users.data.di.getDataModules
import com.ivanbarto.tallerchallenge.users.domain.interactors.UserInteractor
import com.ivanbarto.tallerchallenge.users.domain.interactors.UserInteractorImpl
import org.koin.dsl.module

private val modules = module {
    includes(getDataModules())
    single<UserInteractor> { UserInteractorImpl(get()) }
}

fun getDomainModules() = modules