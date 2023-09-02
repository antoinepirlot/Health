package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import earth.health.data.entity.Day
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.DayWithMeals

@Dao
interface DayDAO {

    @Transaction
    @Query("SELECT * FROM days")
    suspend fun getAll(): List<Day>

    @Query("SELECT * FROM days WHERE day_id = :dayId")
    suspend fun getOne(dayId: Long): Day

    @Transaction
    @Query("SELECT * FROM days WHERE day_id = :dayId")
    suspend fun getOneWithMeals(dayId: Long): DayWithMeals

    @Query("SELECT * FROM meals WHERE meal_id = :id")
    suspend fun getDayWithMeals(id: Long): List<Meal>

    @Upsert
    suspend fun upsert(day: Day)

    @Insert
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun delete(day: Day)

    @Query("SELECT COUNT(day_id) + 1 FROM days")
    suspend fun nextId(): Long

    @Query("SELECT * FROM days ORDER BY day_id DESC LIMIT 1")
    suspend fun getLastDay(): Day

    @Query("SELECT COUNT(*) FROM days")
    suspend fun count(): Long
}