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

    @Delete
    suspend fun delete(food: Food)

    @Update
    suspend fun update(food: Food)

    @Query("SELECT * FROM food")
    suspend fun getAll(): List<Food>
}