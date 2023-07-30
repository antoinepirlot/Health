package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import earth.health.data.entity.Food

@Dao
interface FoodDAO {
    @Insert
    suspend fun insert(food: Food): Long

    @Insert
    suspend fun insertFood(food: Food)

    @Delete
    suspend fun delete(food: Food)

    @Update
    suspend fun update(food: Food)

    @Query("SELECT * FROM foods")
    suspend fun getAll(): List<Food>

    @Query("SELECT * FROM foods WHERE id = :id")
    suspend fun getMealWithFoods(id: Long): List<Food>
}