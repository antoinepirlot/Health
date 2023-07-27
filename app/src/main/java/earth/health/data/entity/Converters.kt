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
    fun fromJsonToMealList(json: String): List<Meal> = deserialize(json)

    @TypeConverter
    fun fromMealListToJson(mealList: List<Meal>): String = serialize(mealList)

    @TypeConverter
    fun fromJsonToLocalDate(localDateString: String): LocalDate = LocalDate.parse(localDateString)

    @TypeConverter
    fun fromLocalDateToJson(localDate: LocalDate): String = localDate.toString()

    @TypeConverter
    fun fromJsonToDay(json: String): Day = deserialize(json)

    @TypeConverter
    fun fromDayToJson(day: Day): String = serialize(day)

    @TypeConverter
    fun fromJsonToFood(json: String): Food = deserialize(json)

    @TypeConverter
    fun fromFoodToJson(food: Food): String = serialize(food)

    @TypeConverter
    fun fromJsonToMeal(json: String): Meal = deserialize(json)

    @TypeConverter
    fun fromMealToJson(meal: Meal): String = serialize(meal)
}

fun <T> serialize(o: T): String = Gson().toJson(o)

fun <T> deserialize(json: String): T = Gson().fromJson(json, object : TypeToken<T>() {}.type)