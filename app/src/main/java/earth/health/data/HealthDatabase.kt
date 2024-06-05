/*
 * This file is part of Health.
 *
 *  Health is free software: you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software Foundation,
 *  either version 3 of the License, or (at your option) any later version.
 *
 *  Health is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with Health.
 *  If not, see <https://www.gnu.org/licenses/>.
 *
 *  **** INFORMATIONS ABOUT THE AUTHOR *****
 *  The author of this file is Antoine Pirlot, the owner of this project.
 *  You find this original project on github.
 *
 *  My github link is: https://github.com/antoinepirlot
 *  This current project's link is: https://github.com/antoinepirlot/Health
 *
 *  You can contact me via my email: pirlot.antoine@outlook.com
 *  PS: I don't answer quickly.
 */

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

/**
 * @author Antoine Pirlot on 24/07/2023
 */

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