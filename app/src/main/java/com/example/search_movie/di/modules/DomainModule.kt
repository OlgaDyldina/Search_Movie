package com.example.search_movie.di.modules

import android.content.Context
import com.example.search_movie.data.MainRepository
import com.example.search_movie.data.preferenes.PreferenceProvider
import com.example.search_movie.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule (val context: Context){
    @Provides
    fun provideContext() = context
    @Singleton
    @Provides
    fun providePreferences(context: Context) = PreferenceProvider(context)
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: com.example.remote_module.TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}
