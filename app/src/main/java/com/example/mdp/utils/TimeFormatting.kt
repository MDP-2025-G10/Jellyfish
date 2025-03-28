package com.example.mdp.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun formatTimestampToDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) // Format: YYYY-MM-DD HH:MM
    return sdf.format(Date(timestamp))
}

fun formatDate(dateStr: String?): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("MMM-d")
        LocalDate.parse(dateStr, inputFormatter).format(outputFormatter)
    } catch (e: Exception) {
        dateStr ?: ""
    }
}