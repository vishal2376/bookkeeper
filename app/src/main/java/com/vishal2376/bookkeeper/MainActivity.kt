package com.vishal2376.bookkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.vishal2376.bookkeeper.presentation.screens.HomeScreen
import com.vishal2376.bookkeeper.ui.theme.BookKeeperTheme
import com.vishal2376.bookkeeper.ui.theme.headingTextStyle
import com.vishal2376.bookkeeper.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookKeeperTheme {
                BookkeeperApp()
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BookkeeperApp() {
        // observe the books data
        val books by viewModel.booksFlow.collectAsState()

        var isAscending by remember { mutableStateOf(true) }

        // Sort books list
        val sortedBooks = if (isAscending) {
            books.sortedBy { it.publishedChapterDate }
        } else {
            books.sortedByDescending { it.publishedChapterDate }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Book Keeper", style = headingTextStyle)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    actions = {
                        IconButton(onClick = { isAscending = !isAscending }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Sort,
                                contentDescription = null
                            )
                        }
                    }
                )
            }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                HomeScreen(books = sortedBooks, viewModel = viewModel)
            }
        }
    }


}