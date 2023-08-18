package earth.health.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import earth.health.data.entity.Day
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.DayWithMeals

@Dao
interface DayDAO {

    @Transaction
    @Query("SELECT * FROM days")
    suspend fun getAll(): List<DayWithMeals>

    @Transaction
    @Query("SELECT * FROM days WHERE day_id = :id")
    suspend fun getDay(id: Long): DayWithMeals

    @Query("SELECT * FROM meals WHERE meal_id = :id")
    suspend fun getDayWithMeals(id: Long): List<Meal>

    @Upsert
    suspend fun upsert(day: Day)

    @Insert
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun delete(day: Day)
}