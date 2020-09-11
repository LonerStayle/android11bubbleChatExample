package com.soo.github.di.module

import com.soo.github.network.remote.GithubApiService
import com.soo.github.network.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataModule {
    @Provides
    fun provideGithubRepository(apiService: GithubApiService) : GithubRepository = GithubRepository(apiService)
}