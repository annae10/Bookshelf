package com.ann.bookshelf.feature_books.data.source.local.dao

import androidx.room.*
import com.ann.bookshelf.feature_books.domain.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

@Query("SELECT * FROM Book")
fun getBooks(): Flow<List<Book>>

@Query("SELECT * FROM Book WHERE id = :id")
suspend fun getBookById(id: Int): Book?

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertBook(book: Book)

@Delete
suspend fun deleteBook(book: Book)


}