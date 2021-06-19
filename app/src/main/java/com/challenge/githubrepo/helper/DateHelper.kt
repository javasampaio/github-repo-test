package com.challenge.githubrepo.helper

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    private const val DATE_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    fun dateToStringFormatted(date: Date): String {
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        return formatter.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun stringToLocalDate(value: String, dateFormat: String = DATE_TIME_ZONE_FORMAT): Date? {
        if(value.isEmpty()) return null
        val parser =  SimpleDateFormat(dateFormat)
        return parser.parse(value)
    }
}