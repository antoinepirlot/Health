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
import androidx.room.Update
import androidx.room.Upsert
import earth.health.data.entity.Food
import earth.health.data.entity.relations.FoodWithMeals

/**
 * @author Antoine Pirlot on 24/07/2023
 */

@Dao
interface FoodDAO {
    @Upsert
    suspend fun upsert(food: Food): Long

    @Insert
    suspend fun insertFood(food: Food)

    @Delete
    suspend fun delete(food: Food)

    @Update
    suspend fun update(food: Food)

    @Transaction
    @Query("SELECT * FROM foods")
    suspend fun getAllWithMeals(): List<FoodWithMeals>

    @Query("SELECT * FROM foods")
    suspend fun getAll(): List<Food>

    @Query("SELECT * FROM foods WHERE food_id = :foodId")
    suspend fun getOne(foodId: Long): Food

    @Transaction
    @Query("SELECT * FROM foods WHERE food_id = :foodId")
    suspend fun getOneWithMeals(foodId: Long): FoodWithMeals
}