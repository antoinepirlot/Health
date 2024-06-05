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

package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import earth.health.data.entity.Day
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.DayWithMeals

@Dao
interface DayDAO {

    @Transaction
    @Query("SELECT * FROM days")
    suspend fun getAll(): List<Day>

    @Query("SELECT * FROM days WHERE day_id = :dayId")
    suspend fun getOne(dayId: Long): Day

    @Transaction
    @Query("SELECT * FROM days WHERE day_id = :dayId")
    suspend fun getOneWithMeals(dayId: Long): DayWithMeals

    @Query("SELECT * FROM meals WHERE meal_id = :id")
    suspend fun getDayWithMeals(id: Long): List<Meal>

    @Upsert
    suspend fun upsert(day: Day)

    @Insert
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun delete(day: Day)

    @Query("SELECT COUNT(day_id) + 1 FROM days")
    suspend fun nextId(): Long

    @Query("SELECT * FROM days ORDER BY day_id DESC LIMIT 1")
    suspend fun getLastDay(): Day

    @Query("SELECT COUNT(*) FROM days")
    suspend fun count(): Long
}