package com.ann.bookshelf.di

import android.app.Application
import androidx.room.Room
import com.ann.bookshelf.feature_books.data.repository.BookRepositoryImpl
import com.ann.bookshelf.feature_books.data.source.local.BookDatabase
import com.ann.bookshelf.feature_books.domain.repository.BookRepository
import com.ann.bookshelf.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookDatabase(app: Application) = Room.databaseBuilder(
        app,
        BookDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: BookDatabase): BookRepository{
        return BookRepositoryImpl(db.bookDao)
    }


}