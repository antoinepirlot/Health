package earth.health.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import earth.health.data.dao.DayDAO
import earth.health.data.dao.FoodDAO
import earth.health.data.dao.MealDAO
import earth.health.data.dao.MealFoodRelDAO
import earth.health.data.entity.Converters
import earth.health.data.entity.Day
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealFoodRel

@Database(entities = [Food::class, Meal::class, Day::class, MealFoodRel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HealthDatabase : RoomDatabase() {
    abstract fun foodDAO(): FoodDAO
    abstract fun mealDAO(): MealDAO
    abstract fun dayDao(): DayDAO
    abstract fun mealFoodRelDao(): MealFoodRelDAO

    companion object {
        @Volatile private var INSTANCE : HealthDatabase? = null

        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                HealthDatabase::class.java,
                "health"
            )
                .addTypeConverter(Converters())
                .build()
            INSTANCE = instance
            instance
        }
    }
}