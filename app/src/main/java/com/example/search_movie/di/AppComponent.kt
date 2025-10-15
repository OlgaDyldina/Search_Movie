package com.example.search_movie.di

import com.example.remote_module.RemoteProvider
import com.example.search_movie.di.modules.DatabaseModule
import com.example.search_movie.di.modules.DomainModule
import com.example.search_movie.viewmodel.HomeFragmentViewModel
import com.example.search_movie.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [RemoteProvider::class],
    modules = [
        com.example.remote_module.RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}