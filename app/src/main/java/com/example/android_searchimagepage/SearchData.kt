package com.example.android_searchimagepage

data class SearchData(
    val imageUrl : String,
    val title : String,
    val dateTime : String,
    var bookMark : Boolean = false
)