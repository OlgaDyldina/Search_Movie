package com.example.search_movie.di

import com.example.search_movie.di.modules.DatabaseModule
import com.example.search_movie.di.modules.DomainModule
import com.example.search_movie.di.modules.RemoteModule
import com.example.search_movie.viewmodel.HomeFragmentViewModel
import com.example.search_movie.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}