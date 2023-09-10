package earth.health.data

import android.content.Context
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import earth.health.R
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
import java.lang.IllegalStateException

const val DATABASE_NAME = "health.db"
const val DATABASE_PATH = "/data/user/0/earth.health/databases/"
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

        fun exportDatabase(context: Context, path: String) {
            val toast = Toast(context)
            toast.setText(R.string.export_passed)
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            val destination = File(path)
            if(destination.exists())
                destination.delete()
            dbFile.copyTo(destination, true)
            toast.show()
        }

        fun importDatabase(context: Context, backupFilePath: String) {
            val toast = Toast(context)
            toast.setText(R.string.import_passed)


            val dbToImport = File(backupFilePath)
            if (dbToImport.isDirectory) {
                throw IllegalStateException("\"$backupFilePath\" is a directory and not a file.")
            }
            val destination = context.getDatabasePath(DATABASE_NAME)
            destination.delete()
            if (destination.isDirectory) {
                throw IllegalStateException("The destination is a directory and not a file.")
            }
            dbToImport.copyTo(destination, true)
            toast.show()
        }
    }
}