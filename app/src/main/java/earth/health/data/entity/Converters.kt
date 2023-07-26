package earth.health.data.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun fromJsonToFoodList(json: String): List<Food> = deserialize(json)

    @TypeConverter
    fun fromFoodListToJson(foodList: List<Food>): String = serialize(foodList)

    @TypeConverter
    fun fromJsonToLocalDate(json: String): LocalDate = deserialize(json)

    @TypeConverter
    fun fromLocalDateToJson(localDate: LocalDate): String = serialize(localDate)
}

fun <T> serialize(o: T): String = Gson().toJson(o)

fun <T> deserialize(json: String): T = Gson().fromJson(json, object : TypeToken<T>() {}.type)