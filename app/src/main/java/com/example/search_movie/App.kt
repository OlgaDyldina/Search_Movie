package com.example.search_movie

import android.app.Application
import com.example.search_movie.di.AppComponent
import com.example.search_movie.di.modules.DatabaseModule
import com.example.search_movie.di.modules.DomainModule
import com.example.search_movie.di.modules.RemoteModule
import okhttp3.internal.Internal.instance


class App : Application() {
        lateinit var dagger: AppComponent

        override fun onCreate() {
            super.onCreate()
            instance = this
            dagger = DaggerAppComponent.builder()
                .remoteModule(RemoteModule())
                .databaseModule(DatabaseModule())
                .domainModule(DomainModule(this))
                .build()
        }

        companion object {
            lateinit var instance: App
                private set
        }
    }