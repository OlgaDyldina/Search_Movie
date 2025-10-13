package com.example.search_movie

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.search_movie.di.AppComponent
import com.example.search_movie.di.modules.DatabaseModule
import com.example.search_movie.di.modules.DomainModule
import com.example.remote_module.RemoteModule
import com.example.search_movie.view.notifications.NotificationConstants.CHANNEL_ID


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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "WatchLaterChannel"
            val descriptionText = "FilmsSearch notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
            }
        }

        companion object {
            lateinit var instance: App
                private set
        }
    }