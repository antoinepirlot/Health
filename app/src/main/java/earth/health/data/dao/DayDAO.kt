package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import earth.health.data.entity.Day
import earth.health.data.entity.Meal

@Dao
interface DayDAO {

    @Query("SELECT * FROM day")
    suspend fun getAll(): List<Day>

    @Query("SELECT * FROM meal WHERE id = :id")
    suspend fun getDayWithMeals(id: Long): List<Meal>

    @Insert
    suspend fun insert(day: Day)

    @Insert
    suspend fun insertMeal(meal: Meal)

    @Update
    suspend fun update(day: Day)

    @Delete
    suspend fun delete(day: Day)
}