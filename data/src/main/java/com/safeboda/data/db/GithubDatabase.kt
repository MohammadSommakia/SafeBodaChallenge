package com.safeboda.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.safeboda.data.db.dao.UserDao
import com.safeboda.data.db.dao.UserKeyDao
import com.safeboda.domain.models.user.model.User

const val DATABASE_NAME = "GithubDatabase.db"

@Database(
    entities = [
        User::class,
        UserKey::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userKeyDao(): UserKeyDao
}