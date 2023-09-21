package com.example.android_searchimagepage

import com.google.gson.annotations.SerializedName

data class SearchDataModel(
    @SerializedName("documents")
    val documents : MutableList<Documents>,
    @SerializedName("meta")
    val metaData : Meta
) {
    data class Documents(
        val collection : String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl : String,
        @SerializedName("image_url")
        val imageUrl : String,
        val width : Int,
        val height : Int,
        @SerializedName("display_sitename")
        val siteName : String,
        @SerializedName("doc_url")
        val docUrl : String,
        val datetime : String
    )
    data class Meta (
        @SerializedName("is_end")
        val isEnd : Boolean,
        @SerializedName("pageable_count")
        val pageableCount : Int,
        @SerializedName("total_count")
        val totalCount : Int
    )
}