package com.ann.bookshelf.feature_books.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ann.bookshelf.feature_books.domain.use_cases.DeleteBook
import com.ann.bookshelf.feature_books.domain.use_cases.GetBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val deleteBook: DeleteBook,
    getBooks: GetBooks
): ViewModel(){

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getBooks().onEach { books ->
            _state.value = state.value.copy(
                books = books
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent){
        when (event) {
            is HomeEvent.DeleteBook -> {
                viewModelScope.launch {
                    deleteBook(event.book)
                }
            }
        }
    }

}