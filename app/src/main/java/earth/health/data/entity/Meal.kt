package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: Meals,
    @ColumnInfo(name = "day_id") var dayId: Long,
    @ColumnInfo(name = "totalKcal") var totalKcal: Int = 0
)

fun getDefaultMeals() = arrayListOf<Meal>(
    Meal(name = Meals.BREAKFAST, dayId = (0).toLong())
)

private fun computeTotalKcal(foodList: List<Food>): Int {
    var totalKcal = 0
    for (food in foodList) {
        totalKcal += food.kcal
    }
    return totalKcal
}
