package com.ann.bookshelf.feature_books.presentation.home

import com.ann.bookshelf.feature_books.domain.model.Book

sealed class HomeEvent{
    data class DeleteBook(val book: Book): HomeEvent()
}
