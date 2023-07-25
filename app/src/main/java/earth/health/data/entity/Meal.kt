package earth.health.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var foodList: List<Food>,
    var date: LocalDateTime = LocalDateTime.now(),
)

fun getDefaultMeals() = arrayListOf<Meal>(
    Meal(name = "Breakfast", foodList = getDefaultFood() ,date = LocalDateTime.now())
)
