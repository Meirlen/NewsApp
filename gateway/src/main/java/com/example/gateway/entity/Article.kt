package com.example.gateway.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(

    val available_supply: String?,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?

) : Parcelable {

    fun empty() = Article(null, null, null, null, null, null, null, null)
}