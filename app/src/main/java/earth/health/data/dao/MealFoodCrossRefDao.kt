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
import androidx.room.Query
import androidx.room.Upsert
import earth.health.data.entity.MealFoodCrossRef

/**
 * @author Antoine Pirlot on 05/08/2023
 */

@Dao
interface MealFoodCrossRefDao {

    @Query("SELECT * FROM meals_foods_cross_ref")
    suspend fun getAll(): List<MealFoodCrossRef>

    @Query("SELECT quantity FROM meals_foods_cross_ref WHERE meal_id = :mealId AND food_id = :foodId")
    suspend fun getQuantity(mealId: Long, foodId: Long): Double

    @Upsert
    suspend fun upsert (mealFoodCrossRef: MealFoodCrossRef)

    @Query("UPDATE meals SET totalKcal = :totalKcal WHERE meal_id = :mealId")
    suspend fun updateMealTotalKcal(mealId: Long, totalKcal: Int)

    @Query("UPDATE days SET total_kcal = total_kcal + :totalKcalToUpdate WHERE day_id = :dayId")
    suspend fun updateDayTotalKcal(dayId: Long, totalKcalToUpdate: Int)

    @Query("DELETE FROM meals_foods_cross_ref WHERE meal_id = :mealId AND food_id = :foodId")
    suspend fun remove(mealId: Long, foodId: Long)
}