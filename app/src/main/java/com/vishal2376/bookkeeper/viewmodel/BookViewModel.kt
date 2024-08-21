package com.vishal2376.bookkeeper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.bookkeeper.data.BookRepository
import com.vishal2376.bookkeeper.data.local.BookEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _booksFlow = MutableStateFlow<List<BookEntity>>(emptyList())
    val booksFlow: StateFlow<List<BookEntity>> = _booksFlow

    init {
        fetchBooks()

        viewModelScope.launch {
            bookRepository.getBooksFlow().collect { books ->
                _booksFlow.value = books
            }
        }
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            bookRepository.fetchBooks()
        }
    }

    fun toggleBookmark(bookId: String, isBookmarked: Boolean) {
        viewModelScope.launch {
            bookRepository.updateBookmarkStatus(bookId, isBookmarked)
        }
    }
}
