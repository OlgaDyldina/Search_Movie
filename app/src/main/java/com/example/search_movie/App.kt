package com.example.search_movie

import android.app.Application
import com.example.search_movie.di.AppComponent
import okhttp3.internal.Internal.instance


class App : Application() {
        lateinit var dagger: AppComponent

        override fun onCreate() {
            super.onCreate()
            instance = this
            dagger = DaggerAppComponent.create()
            }
        }

        companion object {
            lateinit var instance: App
                private set
        }
    }