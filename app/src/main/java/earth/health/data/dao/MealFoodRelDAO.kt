package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Query
import earth.health.data.entity.Food

@Dao
interface MealFoodRelDAO {

    @Query("SELECT * FROM food WHERE id IN (SELECT food_id FROM meals_foods_rel WHERE meal_id = :mealId)")
    suspend fun getAllFoodOf(mealId: Long): List<Food>
}