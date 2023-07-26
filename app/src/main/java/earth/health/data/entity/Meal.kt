package earth.health.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var foodList: List<Food>,
    var date: LocalDate = LocalDate.now(),
)

fun getDefaultMeals() = arrayListOf<Meal>(
    Meal(name = Meals.BREAKFAST, foodList = getDefaultFood())
)
