package earth.health.data.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromJsonToLocalDate(localDateString: String): LocalDate = LocalDate.parse(localDateString)

    @TypeConverter
    fun fromLocalDateToJson(localDate: LocalDate): String = localDate.toString()
}

fun <T> serialize(o: T): String = Gson().toJson(o)

fun <T> deserialize(json: String): T = Gson().fromJson(json, object : TypeToken<T>() {}.type)