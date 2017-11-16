package com.khairilushan.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by khairil on 11/6/17.
 */
data class RepoOwnerEntity(
        @SerializedName("login") val login: String,
        @SerializedName("id") val id: Int,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("html_url") val htmlUrl: String
)