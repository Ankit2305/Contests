package com.example.programmingcontest.core.di

import android.app.Application
import com.example.programmingcontest.core.api.KontestsApi
import com.example.programmingcontest.core.db.ContestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KontestsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideKontestsApi(retrofit: Retrofit): KontestsApi {
        return retrofit.create(KontestsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideContestDatabase(application: Application): ContestDatabase {
        return ContestDatabase.getContestDataBase(application)
    }
}