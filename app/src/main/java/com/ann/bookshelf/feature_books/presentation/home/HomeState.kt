package com.ann.bookshelf.feature_books.presentation.home

import com.ann.bookshelf.feature_books.domain.model.Book

data class HomeState(
    val books: List<Book> = emptyList()
)
