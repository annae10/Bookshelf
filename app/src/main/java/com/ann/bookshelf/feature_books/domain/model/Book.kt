package com.ann.bookshelf.feature_books.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val author: String,
    val year: String
)


