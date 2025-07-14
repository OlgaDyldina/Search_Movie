package com.example.search_movie.di.modules

import com.example.search_movie.data.MainRepository
import com.example.search_movie.data.TmdbApi
import com.example.search_movie.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}
