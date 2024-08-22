package com.vishal2376.bookkeeper.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vishal2376.bookkeeper.data.local.BookEntity
import com.vishal2376.bookkeeper.presentation.components.BookItem
import com.vishal2376.bookkeeper.ui.theme.black900
import com.vishal2376.bookkeeper.ui.theme.blue
import com.vishal2376.bookkeeper.ui.theme.titleTextStyle
import com.vishal2376.bookkeeper.utils.toYear
import com.vishal2376.bookkeeper.viewmodel.BookViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(books: List<BookEntity>, viewModel: BookViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {


        val groupedBooks = books.groupBy { it.publishedChapterDate.toYear() }
        val years = groupedBooks.keys.toList()

        var selectedYearIndex by remember { mutableIntStateOf(0) }

        val scope = rememberCoroutineScope()
        val bookListState = rememberLazyListState()

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp)
        ) {
            items(years) { year ->
                val index = years.indexOf(year)

                Text(
                    text = year.toString(),
                    modifier = Modifier
                        .clickable {
                            selectedYearIndex = index
                            scope.launch {
                                bookListState.animateScrollToItem(
                                    groupedBooks.values
                                        .take(index)
                                        .sumOf { it.size } + index
                                )
                            }
                        }
                        .background(
                            if (selectedYearIndex == index) blue else MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp, 4.dp),
                    color = if (selectedYearIndex == index) black900 else MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        LazyColumn(
            state = bookListState,
            modifier = Modifier.weight(1f)
        ) {
            groupedBooks.forEach { (year, books) ->
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .height(22.dp)
                                .width(5.dp)
                                .background(blue)
                        )
                        Text(
                            text = year.toString(),
                            color = blue,
                            style = titleTextStyle,
                        )
                    }
                }
                items(books) { book ->
                    BookItem(book = book, onBookmarkClick = { id, isBookmarked ->
                        viewModel.toggleBookmark(id, isBookmarked)
                    })
                }
            }
        }
    }
}