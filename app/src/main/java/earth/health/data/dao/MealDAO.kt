package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods

@Dao
interface MealDAO {

    @Insert
    suspend fun insert(meal: Meal): Long

    @Delete
    suspend fun delete(meal: Meal)

    @Update
    suspend fun update(meal: Meal)

    @Query("SELECT * FROM meals")
    suspend fun getAll(): List<Meal>

    @Transaction
    @Query("SELECT * FROM meals JOIN foods")
    fun getMealWithFoods(): List<MealWithFoods>
}