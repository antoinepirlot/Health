package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(indices = [Index(value = ["date"], unique = true)])
data class Day(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "date") var date: LocalDate = LocalDate.now(),
)
