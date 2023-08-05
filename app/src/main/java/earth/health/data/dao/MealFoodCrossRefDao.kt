package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Insert
import earth.health.data.entity.MealFoodCrossRef

@Dao
interface MealFoodCrossRefDao {
    @Insert
    suspend fun insert (mealFoodCrossRef: MealFoodCrossRef)
}