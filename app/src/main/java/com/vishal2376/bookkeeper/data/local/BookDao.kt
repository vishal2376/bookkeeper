package com.vishal2376.bookkeeper.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooksFlow(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Query("UPDATE books SET isBookmarked = :isBookmarked WHERE id = :bookId")
    suspend fun updateBookmarkStatus(bookId: String, isBookmarked: Boolean)
}