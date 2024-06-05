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
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods

/**
 * @author Antoine Pirlot on 25/07/2023
 */

@Dao
interface MealDAO {

    @Upsert
    suspend fun upsert(meal: Meal): Long

    @Delete
    suspend fun delete(meal: Meal)

    @Update
    suspend fun update(meal: Meal)

    @Query("SELECT * FROM meals")
    suspend fun getAll(): List<Meal>

    @Transaction
    @Query("SELECT * FROM meals")
    suspend fun getAllWithFoods(): List<MealWithFoods>

    @Query("SELECT * FROM meals WHERE meal_id = :mealId")
    suspend fun getOne(mealId: Long): Meal

    @Transaction
    @Query("SELECT * FROM meals WHERE meal_id = :mealId")
    suspend fun getOneWithFoods(mealId: Long): MealWithFoods
}