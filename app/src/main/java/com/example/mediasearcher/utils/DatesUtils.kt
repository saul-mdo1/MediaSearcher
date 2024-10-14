package com.example.mediasearcher.utils

import android.annotation.SuppressLint
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
fun formatDate(dateString: String?, locale: Locale = Locale.getDefault()): String? {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy", locale)
        val date = LocalDate.parse(dateString, inputFormatter)
        date.format(outputFormatter).lowercase(locale)
    } catch (e: Exception) {
        Timber.d("DatesUtils_TAG: formatDateString: ERROR: ${e.message} ")
        dateString

    }
}