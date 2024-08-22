package com.vishal2376.bookkeeper.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vishal2376.bookkeeper.R
import com.vishal2376.bookkeeper.data.local.BookEntity
import com.vishal2376.bookkeeper.ui.theme.bodyTextStyle
import com.vishal2376.bookkeeper.ui.theme.titleTextStyle
import com.vishal2376.bookkeeper.ui.theme.yellow
import com.vishal2376.bookkeeper.ui.theme.yellowDark
import com.vishal2376.bookkeeper.utils.toFormattedDate

@Composable
fun BookItem(book: BookEntity, onBookmarkClick: (String, Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .width(90.dp)
                .height(130.dp),
            model = book.image,
            placeholder = painterResource(id = R.drawable.broken_image),
            error = painterResource(id = R.drawable.broken_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = book.title,
                style = titleTextStyle,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) yellow else yellowDark
                )
                Text(
                    text = book.score.toString(),
                    style = bodyTextStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = book.publishedChapterDate.toFormattedDate(),
                    style = bodyTextStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
        IconButton(onClick = { onBookmarkClick(book.id, !book.isBookmarked) }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = if (book.isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}