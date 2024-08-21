package com.vishal2376.bookkeeper.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vishal2376.bookkeeper.data.local.BookEntity
import com.vishal2376.bookkeeper.presentation.components.BookItem
import com.vishal2376.bookkeeper.viewmodel.BookViewModel

@Composable
fun HomeScreen(books: List<BookEntity>, viewModel: BookViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            // display list of books
            items(books) { book ->
                BookItem(book = book, onBookmarkClick = { id, isBookmarked ->
                    viewModel.toggleBookmark(id, isBookmarked)
                })
            }
        }
    }
}

