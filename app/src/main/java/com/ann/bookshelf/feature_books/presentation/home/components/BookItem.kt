package com.ann.bookshelf.feature_books.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ann.bookshelf.feature_books.domain.model.Book
import com.ann.bookshelf.feature_books.presentation.home.HomeEvent

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    book: Book,
    onEditBook: () -> Unit,
    onDeleteBook: () -> Unit
    ){
    Card(
      modifier = modifier.fillMaxWidth()
          .padding(horizontal = 14.dp, vertical = 12.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row(
            modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "${book.title}, ${book.author}, ${book.year.toString()}",
                    style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = book.description.toString(),
                style = MaterialTheme.typography.caption.copy(color = Color.DarkGray)
            )
        }
        Row {
            IconButton(onClick = onEditBook){
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
            IconButton(onClick = onDeleteBook){
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}