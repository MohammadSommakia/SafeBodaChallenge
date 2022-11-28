package com.safeboda.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserKey(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    var prev: Int?,
    var next: Int?
)
