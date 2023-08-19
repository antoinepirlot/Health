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