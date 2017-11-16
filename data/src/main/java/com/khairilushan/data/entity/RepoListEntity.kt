package com.khairilushan.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by khairil on 11/6/17.
 */
data class RepoListEntity(
        @SerializedName("total_count") val totalCount: Int,
        @SerializedName("incomplete_result") val incompleteResult: Boolean,
        @SerializedName("items") val items: List<RepoEntity>
)