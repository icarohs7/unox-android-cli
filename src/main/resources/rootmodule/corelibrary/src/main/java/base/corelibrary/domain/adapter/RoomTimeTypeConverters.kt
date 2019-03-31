package base.corelibrary.domain.adapter

import java.util.Date

/**
 * Room type converter for
 * time based types
 */
class RoomTimeTypeConverters {
    @TypeConverter
    fun fromDate(data: Date): Long {
        return data.time
    }

    @TypeConverter
    fun toDate(data: Long): Date {
        return Date(data)
    }

    @TypeConverter
    fun fromPeriod(data: Period): Int {
        return data.millis
    }

    @TypeConverter
    fun toPeriod(data: Int): Period {
        return Period.millis(data)
    }

    @TypeConverter
    fun fromDuration(data: Duration): Long {
        return data.millis
    }

    @TypeConverter
    fun toDuration(data: Long): Duration {
        return data.milliDuration()
    }
}
