package earth.health.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import earth.health.data.dao.FoodDAO
import earth.health.data.dao.MealDAO
import earth.health.data.entity.Food
import earth.health.data.entity.Meal

@Database(entities = [Food::class, Meal::class], version = 1, exportSchema = false)
abstract class HealthDatabase : RoomDatabase() {
    abstract fun foodDAO(): FoodDAO
    abstract fun mealDAO(): MealDAO

    companion object {
        @Volatile private var INSTANCE : HealthDatabase? = null

        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                HealthDatabase::class.java,
                "health"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}