package com.safeboda.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safeboda.domain.models.user.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(list: List<User>)

    @Query("SELECT * FROM User")
    fun getAllUsers(): PagingSource<Int, User>

    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()
}