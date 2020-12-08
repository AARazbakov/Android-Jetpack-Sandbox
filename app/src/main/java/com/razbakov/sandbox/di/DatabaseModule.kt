package com.razbakov.sandbox.di

import android.content.Context
import com.razbakov.sandbox.database.AppDatabase
import com.razbakov.sandbox.database.dao.HealthCheckDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

    @Provides
    fun provideHealthCheckDao(appDatabase: AppDatabase): HealthCheckDao = appDatabase.healthCheckDao()
}
