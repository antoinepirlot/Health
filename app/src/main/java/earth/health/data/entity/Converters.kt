package earth.health.data.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun fromJsonToFoodList(json: String): List<Food> {
        return deserialize(json)
    }

    @TypeConverter
    fun fromFoodListToJson(foodList: List<Food>): String {
        return serialize(foodList)
    }

    @TypeConverter
    fun fromJsonToLocalDate(json: String): LocalDate {
        return deserialize(json)
    }

    @TypeConverter
    fun fromLocalDateToJson(localDateTime: LocalDate): String {
        return serialize(localDateTime)
    }
}

fun <T> serialize(o: T): String = Gson().toJson(o)

fun <T> deserialize(json: String): T {
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson<T>(json, type)
}