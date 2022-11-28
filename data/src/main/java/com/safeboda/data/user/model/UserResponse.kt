package com.safeboda.data.user.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") var id: Long,
    @SerializedName("avatar_url") var avatarUrl: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("score") var score: Float?,
    @SerializedName("name") var login: String?,
    @SerializedName("company") var company: String?,
    @SerializedName("blog") var blog: String?,
    @SerializedName("location") var location: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("bio") var bio: String?,
    @SerializedName("public_repos") var publicRepos: Int?,
    @SerializedName("public_gists") var publicGists: Int?,
    @SerializedName("followers") var followers: Int?,
    @SerializedName("following") var following: Int?,
)