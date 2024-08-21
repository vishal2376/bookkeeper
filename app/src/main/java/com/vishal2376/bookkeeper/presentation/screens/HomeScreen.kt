package com.vishal2376.bookkeeper.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vishal2376.bookkeeper.ui.theme.BookKeeperTheme

@Composable
fun HomeScreen() {
    Text(text = "Hello")
}

@Preview
@Composable
private fun HomeScreenPreview() {
    BookKeeperTheme {
        HomeScreen()
    }
}