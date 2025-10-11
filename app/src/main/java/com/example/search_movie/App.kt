package com.example.search_movie

import android.app.Application
import com.example.search_movie.di.AppComponent
import com.example.search_movie.di.modules.DatabaseModule
import com.example.search_movie.di.modules.DomainModule
import com.example.remote_module.RemoteModule


class App : Application() {
        lateinit var dagger: AppComponent

        override fun onCreate() {
            super.onCreate()
            instance = this
            val remoteProvider = DaggerRemoteComponent.create()
            dagger = DaggerAppComponent.builder()
                .remoteModule(com.example.remote_module.RemoteModule())
                .databaseModule(DatabaseModule())
                .domainModule(DomainModule(this))
                .build()
        }

        companion object {
            lateinit var instance: App
                private set
        }
    }