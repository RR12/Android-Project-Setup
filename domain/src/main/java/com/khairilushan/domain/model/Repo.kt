package com.khairilushan.domain.model

/**
 * Created by khairil on 11/6/17.
 */
data class Repo(
        val name: String,
        val fullName: String,
        val description: String?,
        val pageUrl: String,
        val ownerName: String,
        val ownerAvatarUrl: String,
        val ownerPageUrl: String
)