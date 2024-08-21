package com.vishal2376.bookkeeper.domain.model

import com.vishal2376.bookkeeper.data.local.BookEntity

data class Book(
    val id: String,
    val image: String,
    val score: Double,
    val popularity: Int,
    val title: String,
    val publishedChapterDate: Long
) {
    fun toBook() = Book(id, image, score, popularity, title, publishedChapterDate)
    fun toEntity() = BookEntity(id, image, score, popularity, title, publishedChapterDate, false)
}
