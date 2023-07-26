package earth.health.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import earth.health.data.entity.Day

@Dao
interface DayDAO {

    @Query("SELECT * FROM day")
    suspend fun getAll(): List<Day>

    @Insert
    suspend fun insert(day: Day)

    @Update
    suspend fun update(day: Day)

    @Delete
    suspend fun delete(day: Day)
}