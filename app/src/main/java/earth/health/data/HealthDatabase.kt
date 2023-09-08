package earth.health.data

import android.content.Context
import android.widget.Toast
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
import java.io.File

const val DATABASE_NAME = "health"
const val BACKUP_PATH = "/storage/emulated/0/Documents/"

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

        fun exportDatabase(context: Context) {
            //TODO
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            val bkpFile = File("$BACKUP_PATH$DATABASE_NAME")
            if(bkpFile.exists())
                bkpFile.delete()
            dbFile.copyTo(bkpFile, true)
        }
    }
}