package com.safeboda.data.di

import android.app.Application
import androidx.room.Room
import com.safeboda.data.db.DATABASE_NAME
import com.safeboda.data.db.GithubDatabase
import com.safeboda.data.db.dao.UserDao
import com.safeboda.data.db.dao.UserKeyDao
import com.safeboda.domain.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class PersistenceModule(private val application: Application) {

    private var githubDatabase: GithubDatabase =
        Room.databaseBuilder(application, GithubDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()



    @AppScope
    @Provides
    fun provideGithubDatabase(): GithubDatabase = githubDatabase

    @AppScope
    @Provides
    fun provideUserDao(githubDatabase: GithubDatabase): UserDao =
        githubDatabase.userDao()


    @AppScope
    @Provides
    fun provideUserKeyDao(githubDatabase: GithubDatabase): UserKeyDao =
        githubDatabase.userKeyDao()

}