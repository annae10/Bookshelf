package com.ann.bookshelf.feature_books.data.repository

import com.ann.bookshelf.feature_books.data.source.local.dao.BookDao
import com.ann.bookshelf.feature_books.domain.model.Book
import com.ann.bookshelf.feature_books.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val dao: BookDao
): BookRepository {
    override fun getBooks(): Flow<List<Book>> {
        return dao.getBooks()
    }

    override suspend fun getBookById(id: Int): Book? {
        return dao.getBookById(id)
    }

    override suspend fun insertBook(book: Book) {
        dao.insertBook(book)
    }

    override suspend fun deleteBook(book: Book) {
       dao.deleteBook(book)
    }

}