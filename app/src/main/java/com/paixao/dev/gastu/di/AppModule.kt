package com.paixao.dev.gastu.di

import android.app.Application
import androidx.room.Room
import com.paixao.dev.gastu.data.local.AppDatabase
import com.paixao.dev.gastu.data.repository.DealRepositoryImpl
import com.paixao.dev.gastu.data.repository.UserRepositoryImpl
import com.paixao.dev.gastu.domain.repository.UserRepository
import com.paixao.dev.gastu.domain.repository.DealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    internal fun provideUserSpendRepository(
        db: AppDatabase
    ): DealRepository {
        return DealRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    internal fun provideUserRepository(
        db: AppDatabase
    ): UserRepository {
        return UserRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "gastu_db")
            .build()
    }
}
