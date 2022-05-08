package com.ann.bookshelf.feature_books.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ann.bookshelf.feature_books.domain.model.Book
import com.ann.bookshelf.feature_books.presentation.Screen
import com.ann.bookshelf.feature_books.presentation.home.components.BookItem
import com.ann.bookshelf.R

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel:HomeViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            HomeFab(
                onFabClicked = { navController.navigate(Screen.Edit.route)}
            )
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeleteBook = { viewModel.onEvent(HomeEvent.DeleteBook(it))},
                onEditBook = {
                    navController.navigate(
                        route = Screen.Edit.passId(it)
                    )
                },
                books = state.books
            )
        }
    )
}



@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.books),
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onDeleteBook: (book: Book) -> Unit,
    onEditBook: (id: Int?) -> Unit,
    books: List<Book> = emptyList()
) {
    Surface (
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        LazyColumn{
            items(books){book ->
                BookItem(
                    book = book,
                    onEditBook = { onEditBook(book.id)},
                    onDeleteBook = {onDeleteBook(book)}
                )

            }
        }
    }
}


@Composable
fun HomeFab(
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit = {  }
) {

    FloatingActionButton(
        onClick = onFabClicked,
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        backgroundColor = MaterialTheme.colors.primary
    ){
        Icon(imageVector = Icons.Outlined.Add, contentDescription = stringResource(id = R.string.add_book) )
    }

}