package com.safeboda.data.mapper

import com.safeboda.data.user.model.UserResponse
import com.safeboda.domain.models.user.model.User

fun List<UserResponse>.toDomain(): List<User> {

    return map {
        User(
            id = it.id,
            avatarUrl = it.avatarUrl ?: "",
            url = it.url ?: "",
            type = it.type ?: "",
            score = it.score ?: 0f,
            name = it.login ?: "",
            company = it.company ?: "",
            blog = it.blog ?: "",
            location = it.location ?: "",
            email = it.email ?: "",
            bio = it.bio ?: "",
            publicRepos = it.publicRepos ?: 0,
            publicGists = it.publicGists ?: 0,
            followers = it.followers ?: 0,
            following = it.following ?: 0,
        )
    }

}

