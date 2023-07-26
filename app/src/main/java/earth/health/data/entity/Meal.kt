package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: Meals,
    var foodList: List<Food> = listOf(),
    var date: LocalDate = LocalDate.now(),
    var totalKcal: Int = computeTotalKcal(foodList)
)

fun getDefaultMeals() = arrayListOf<Meal>(
    Meal(name = Meals.BREAKFAST)
)

private fun computeTotalKcal(foodList: List<Food>): Int {
    var totalKcal = 0
    for (food in foodList) {
        totalKcal += food.kcal
    }
    return totalKcal
}
