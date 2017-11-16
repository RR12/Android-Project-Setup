package com.khairilushan.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.khairilushan.domain.model.Repo

/**
 * Created by khairil on 11/6/17.
 */
@Entity(tableName = "repo")
data class RepoEntity(
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id: Int,
        @ColumnInfo(name = "name") @SerializedName("name") val name: String,
        @ColumnInfo(name = "full_name") @SerializedName("full_name") val fullName: String,
        @Embedded(prefix = "owner") @SerializedName("owner") val owner: RepoOwnerEntity,
        @ColumnInfo(name = "html_url") @SerializedName("html_url") val htmlUrl: String,
        @ColumnInfo(name = "description") @SerializedName("description") val description: String?
) {
    fun toRepo(): Repo {
        return Repo(
                name = name,
                fullName = fullName,
                pageUrl = htmlUrl,
                description = description,
                ownerName = owner.login,
                ownerAvatarUrl = owner.avatarUrl,
                ownerPageUrl = owner.htmlUrl
        )
    }
}