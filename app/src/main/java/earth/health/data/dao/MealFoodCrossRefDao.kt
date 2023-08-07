package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import earth.health.data.entity.MealFoodCrossRef

@Dao
interface MealFoodCrossRefDao {
    @Query("SELECT quantity FROM meals_foods_cross_ref WHERE meal_id = :mealId AND food_id = :foodId")
    suspend fun getQuantity(mealId: Long, foodId: Long): Double

    @Insert
    suspend fun insert (mealFoodCrossRef: MealFoodCrossRef)
}