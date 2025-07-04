package com.ivanbarto.tallerchallenge.di

import android.app.Application
import com.ivanbarto.tallerchallenge.users.presentation.di.getPresentationModules
import org.koin.core.context.startKoin

class ApplicationActivity : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                getPresentationModules()
            )
        }
    }
}