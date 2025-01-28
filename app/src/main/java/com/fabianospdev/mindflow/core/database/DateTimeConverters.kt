package com.fabianospdev.mindflow.core.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class DateTimeConverters {

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime?): String? {
        return localDateTime?.toString()
    }

    @TypeConverter
    fun toLocalDateTime(dateTimeString: String?): LocalDateTime? {
        return dateTimeString?.let { LocalDateTime.parse(it) }
    }
}
