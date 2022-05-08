package com.ann.bookshelf.feature_books.domain.use_cases

import com.ann.bookshelf.feature_books.domain.model.Book
import com.ann.bookshelf.feature_books.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooks @Inject constructor(
    private val repository: BookRepository
){
    operator fun invoke(): Flow<List<Book>>{
        return repository.getBooks()
    }
}