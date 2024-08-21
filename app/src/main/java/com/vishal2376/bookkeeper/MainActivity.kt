package com.vishal2376.bookkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vishal2376.bookkeeper.presentation.screens.HomeScreen
import com.vishal2376.bookkeeper.ui.theme.BookKeeperTheme
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

        BookKeeperTheme {
            Scaffold(topBar = { TopAppBar(title = { Text("Book Keeper") }) }) {
                HomeScreen(books = books, viewModel = viewModel)
            }
        }
    }


}