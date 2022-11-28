package com.safeboda.domain.models.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    var avatarUrl: String,
    var url: String,
    var type: String,
    var score: Float,
    var name: String,
    var company: String,
    var blog: String,
    var location: String,
    var email: String,
    var bio: String,
    var publicRepos: Int,
    var publicGists: Int,
    var followers: Int,
    var following: Int,
)