package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import earth.health.data.entity.FoodMealRel

@Dao
interface FoodMealRelDAO {
    @Query("SELECT * FROM foodmealrel")
    suspend fun getAll(): List<FoodMealRel>

    @Insert
    suspend fun insert(foodMealRel: FoodMealRel)

    @Update
    suspend fun update(foodMealRel: FoodMealRel)

    @Delete
    suspend fun delete(foodMealRel: FoodMealRel)
}