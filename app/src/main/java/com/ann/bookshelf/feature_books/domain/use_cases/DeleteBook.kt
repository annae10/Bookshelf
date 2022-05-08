package com.ann.bookshelf.feature_books.domain.use_cases

import com.ann.bookshelf.feature_books.domain.model.Book
import com.ann.bookshelf.feature_books.domain.repository.BookRepository
import javax.inject.Inject

class DeleteBook @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(book: Book){
        repository.deleteBook(book)
    }
}