package com.safeboda.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safeboda.data.db.UserKey

@Dao
interface UserKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsersKeys(l: List<UserKey>)

    @Query("DELETE FROM UserKey")
    suspend fun deleteAllBlogKey()

    @Query("SELECT * FROM UserKey WHERE id=:id")
    suspend fun getAllKeys(id: Long): UserKey
}