package base.corelibrary.domain.adapter

/**
 * Type converters using json format
 * as serializer and serializer for
 * a given type, use as the last
 * converter
 */
class RoomJsonTypeConverters {
    @TypeConverter
    fun fromListString(data: List<String>): String {
        return Json.stringify(String.serializer().list, data)
    }

    @TypeConverter
    fun toListString(data: String): List<String> {
        return Json.parse(String.serializer().list, data)
    }
}