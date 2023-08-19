package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods

@Dao
interface MealDAO {

    @Upsert
    suspend fun upsert(meal: Meal): Long

    @Delete
    suspend fun delete(meal: Meal)

    @Update
    suspend fun update(meal: Meal)

    @Transaction
    @Query("SELECT * FROM meals")
    suspend fun getAll(): List<MealWithFoods>

    @Query("SELECT * FROM meals WHERE meal_id = :mealId")
    suspend fun getOne(mealId: Long): MealWithFoods
}