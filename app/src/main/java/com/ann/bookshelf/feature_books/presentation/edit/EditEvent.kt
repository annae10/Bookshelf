package com.ann.bookshelf.feature_books.presentation.edit

sealed class EditEvent {
    data class EnteredTitle(val value: String): EditEvent()
    data class EnteredAuthor(val value: String): EditEvent()
    data class EnteredYear(val value: String): EditEvent()
    data class EnteredDescription(val value: String): EditEvent()
    object InsertBook: EditEvent()
}