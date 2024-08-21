package com.vishal2376.bookkeeper.data.remote

import com.vishal2376.bookkeeper.domain.model.Book
import retrofit2.http.GET

interface ApiService {
    @GET("b/CNGI")
    suspend fun getBooks(): List<Book>
}