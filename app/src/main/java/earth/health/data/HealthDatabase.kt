package earth.health.data

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import earth.health.data.dao.DayDAO
import earth.health.data.dao.FoodDAO
import earth.health.data.dao.MealDAO
import earth.health.data.dao.MealFoodCrossRefDao
import earth.health.data.entity.Converters
import earth.health.data.entity.Day
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.MealFoodCrossRef

const val DATABASE_NAME = "health"

@Database(entities = [Food::class, Meal::class, Day::class, MealFoodCrossRef::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HealthDatabase : RoomDatabase() {
    abstract fun foodDAO(): FoodDAO
    abstract fun mealDAO(): MealDAO
    abstract fun dayDao(): DayDAO
    abstract fun mealFoodCrossRefDao(): MealFoodCrossRefDao

    companion object {
        @Volatile private var INSTANCE : HealthDatabase? = null

        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                HealthDatabase::class.java,
                DATABASE_NAME
            )
                .addTypeConverter(Converters())
                .build()
            INSTANCE = instance
            instance
        }

        fun exportDatabase(context: Context, uri: Uri): Boolean {
            val databaseFile = context.getDatabasePath(DATABASE_NAME)
            return copyTo(context = context, from = databaseFile.toUri(), to = uri)
        }

        fun importDatabase(context: Context, uri: Uri): Boolean {
            val databaseFile = context.getDatabasePath(DATABASE_NAME)
            if (databaseFile.exists())
                databaseFile.delete()
            return copyTo(context = context, from = uri, to = databaseFile.toUri())
        }

        private fun copyTo(context:Context, from: Uri, to: Uri): Boolean {
            return try {
                context.contentResolver.openInputStream(from)?.use { input ->
                    context.contentResolver.openOutputStream(to)?.use { out ->
                        input.copyTo(out = out)
                    }
                }
                true
            } catch (_: Exception) {
                false
            }
        }
    }
}