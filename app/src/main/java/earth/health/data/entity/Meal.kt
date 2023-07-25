package earth.health.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
//    var foodList: List<Food>?,
//    var date: LocalDateTime = LocalDateTime.now(),
)

fun getDefaultMeals() = listOf<Meal>(
    Meal(name = "Breakfast")
)
