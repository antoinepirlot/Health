package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("meal_id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: Meals,

    @ColumnInfo(name = "day_id")
    var dayId: Long,

    @ColumnInfo(name = "totalKcal")
    var totalKcal: Int = 0,

)

fun getDefaultMeals() = arrayListOf(
    Meal(name = Meals.BREAKFAST, dayId = (0).toLong())
)

private fun computeTotalKcal(foodList: List<Food>): Int {
    var totalKcal = 0
    for (food in foodList) {
        totalKcal += food.kcal
    }
    return totalKcal
}

fun getBlankMeal() = Meal(name = Meals.BREAKFAST, dayId = 0)
