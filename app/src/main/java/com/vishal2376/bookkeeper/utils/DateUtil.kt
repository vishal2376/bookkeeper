package com.vishal2376.bookkeeper.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// format date
fun Long.toFormattedDate(): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return sdf.format(Date(this * 1000))
}

// get year
fun Long.toYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000
    return calendar.get(Calendar.YEAR)
}