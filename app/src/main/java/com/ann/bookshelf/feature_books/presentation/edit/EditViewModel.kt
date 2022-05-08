package com.ann.bookshelf.feature_books.presentation.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ann.bookshelf.feature_books.domain.model.Book
import com.ann.bookshelf.feature_books.domain.use_cases.GetBook
import com.ann.bookshelf.feature_books.domain.use_cases.InsertBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getBook: GetBook,
    private val insertBook: InsertBook,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _bookTitle = mutableStateOf(TextFieldState())
    val bookTitle: State<TextFieldState> = _bookTitle

    private val _bookAuthor = mutableStateOf(TextFieldState())
    val bookAuthor: State<TextFieldState> = _bookAuthor

    private val _bookYear = mutableStateOf(TextFieldState())
    val bookYear: State<TextFieldState> = _bookYear

    private val _bookDescription = mutableStateOf(TextFieldState())
    val bookDescription: State<TextFieldState> = _bookDescription

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentBookId: Int? = null

    init {
        savedStateHandle.get<Int>("bookId")?.let{ bookId ->
            if (bookId != -1){
                viewModelScope.launch{
                    getBook(bookId)?.also{ book ->
                        currentBookId = book.id
                        _bookTitle.value = bookTitle.value.copy(
                            text = book.title
                        )
                        _bookAuthor.value = bookAuthor.value.copy(
                            text = book.author
                        )
                        _bookYear.value = bookYear.value.copy(
                            text = book.year.toString()
                        )
                        _bookDescription.value = bookDescription.value.copy(
                            text = book.description
                        )

                    }
                }
            }
        }
    }

    fun onEvent(event: EditEvent){
        when (event){
            is EditEvent.EnteredTitle ->{
                _bookTitle.value = bookTitle.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredAuthor ->{
                _bookAuthor.value = bookAuthor.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredYear ->{
                _bookYear.value = bookYear.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredDescription ->{
                _bookDescription.value = bookDescription.value.copy(
                    text = event.value
                )
            }
            EditEvent.InsertBook ->{
                viewModelScope.launch{
                    insertBook(
                        Book(
                           title = bookTitle.value.text,
                           author = bookAuthor.value.text,
                           year = bookYear.value.text.toInt(),
                           description = bookDescription.value.text,
                            id = currentBookId
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveBook)
                }
            }
        }

    }

    sealed class UiEvent {
        object SaveBook: UiEvent ()
    }
}