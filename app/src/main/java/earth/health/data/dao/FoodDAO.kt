package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import earth.health.data.entity.Food
import earth.health.data.entity.relations.FoodWithMeals

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

    @Transaction
    @Query("SELECT * FROM foods")
    suspend fun getAll(): List<FoodWithMeals>
}