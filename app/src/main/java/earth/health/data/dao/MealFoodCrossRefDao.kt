package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import earth.health.data.entity.MealFoodCrossRef

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