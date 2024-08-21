package com.vishal2376.bookkeeper.data

import android.util.Log
import com.vishal2376.bookkeeper.data.local.BookDao
import com.vishal2376.bookkeeper.data.local.BookEntity
import com.vishal2376.bookkeeper.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val apiService: ApiService
) {

    // get list of books stored in database
    fun getBooksFlow(): Flow<List<BookEntity>> = bookDao.getAllBooksFlow()

    // fetch books from api and store it into database
    suspend fun fetchBooks() {
        try {
            val booksFromApi = apiService.getBooks().map { it.toBook() }
            bookDao.insertBooks(booksFromApi.map { it.toEntity() })
        } catch (e: Exception) {
            Log.e("ERROR", "fetchBooks: ${e.message}")
        }
    }

    // update bookmark status of book in database
    suspend fun updateBookmarkStatus(bookId: String, isBookmarked: Boolean) {
        bookDao.updateBookmarkStatus(bookId, isBookmarked)
    }
}
