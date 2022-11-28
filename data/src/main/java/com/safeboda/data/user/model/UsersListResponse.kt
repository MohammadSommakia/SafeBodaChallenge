package com.safeboda.data.user.model

import com.google.gson.annotations.SerializedName

data class UsersListResponse(
    @SerializedName("total_count") var totalCount: Long,
    @SerializedName("incomplete_results") var incompleteResults: Boolean,
    @SerializedName("items") var items: List<UserResponse>
)
