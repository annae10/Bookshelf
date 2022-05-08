package com.ann.bookshelf.feature_books.domain.repository

import com.ann.bookshelf.feature_books.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getBooks(): Flow<List<Book>>

    suspend fun getBookById(id: Int): Book?

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(book: Book)
}