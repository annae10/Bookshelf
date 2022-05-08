package com.ann.bookshelf.feature_books.presentation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Edit: Screen("edit?bookId={bookId}"){
        fun passId(bookId: Int?): String {
            return "edit?bookId=$bookId"
        }
    }
}