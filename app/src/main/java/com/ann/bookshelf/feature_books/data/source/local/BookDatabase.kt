package com.ann.bookshelf.feature_books.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ann.bookshelf.feature_books.data.source.local.dao.BookDao
import com.ann.bookshelf.feature_books.domain.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)

abstract class BookDatabase: RoomDatabase() {
    abstract val bookDao: BookDao
}