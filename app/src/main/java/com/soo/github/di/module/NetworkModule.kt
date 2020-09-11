package com.soo.github.di.module

import com.soo.github.constants.Constants.SERVER_HOST
import com.soo.github.network.remote.GithubApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor) =
        OkHttpClient.Builder().apply { addInterceptor(logger) }.build()

    @Provides
    @Singleton
    fun provideRxJavaAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideGitHubHost(
        client: OkHttpClient,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(SERVER_HOST)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: Retrofit): GithubApiService = retrofit.create(GithubApiService::class.java)

}