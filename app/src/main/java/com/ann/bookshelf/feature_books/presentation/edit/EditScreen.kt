package com.ann.bookshelf.feature_books.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ann.bookshelf.feature_books.presentation.edit.components.BookInputText
import com.ann.bookshelf.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditScreen(
    navController: NavController,
    viewModel:EditViewModel = hiltViewModel()
){

    val titleState = viewModel.bookTitle.value
    val authorState = viewModel.bookAuthor.value
    val yearState = viewModel.bookYear.value
    val descriptionState = viewModel.bookDescription.value

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when (event){
                is EditViewModel.UiEvent.SaveBook -> {
                    navController.navigateUp()
                }
            }

        }
    }

    Scaffold (
        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.add_book)
            )
        }, content = {
            EditContent(
                title = titleState.text,
                author = authorState.text,
                year = yearState.text,
                description = descriptionState.text,
                 onEvent = {viewModel.onEvent(it)}
            )
        },
        bottomBar = {
            EditBottomBar(
                onInsertBook = { viewModel.onEvent(EditEvent.InsertBook)}
            )
        }
    )
}




@Composable
fun EditTopBar(topAppBarText: String) {

    TopAppBar(
        title = {
        Text(
           text = topAppBarText,
           textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)

        )
    },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun EditContent(
    title: String,
    author: String,
    year: String,
    description: String,
    onEvent: (EditEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
    Spacer(modifier = Modifier.height(44.dp))
    BookInputText(
        text = title,
        hint = stringResource(id = R.string.title),
        onTextChange = {onEvent(EditEvent.EnteredTitle(it))}
    )
    BookInputText(
        text = author,
        hint = stringResource(id = R.string.author),
        onTextChange = {onEvent(EditEvent.EnteredAuthor(it))}
    )
    BookInputText(
        text = year,
        hint = stringResource(id = R.string.year),
        onTextChange = {onEvent(EditEvent.EnteredYear(it))}
    )
    BookInputText(
        text = description,
        hint = stringResource(id = R.string.description),
        onTextChange = {onEvent(EditEvent.EnteredDescription(it))}
    )
}
}

@Composable
fun EditBottomBar(
    modifier: Modifier = Modifier,
    onInsertBook: () -> Unit
) {
   Button(
       modifier = modifier
           .fillMaxWidth()
           .padding(horizontal = 10.dp, vertical = 14.dp),
       onClick = { onInsertBook()}
   ) {
       Text(text = stringResource(id = R.string.add_book))
   }
}