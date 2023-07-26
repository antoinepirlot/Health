package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(foreignKeys = [ForeignKey(entity = Day::class, parentColumns = ["id"], childColumns = ["day"],onDelete = ForeignKey.CASCADE)])
data class Meal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: Meals,
    @ColumnInfo(name = "foodList") var foodList: List<Food> = listOf(),
    @ColumnInfo(name = "day") var day: Int,
    @ColumnInfo(name = "totalKcal") var totalKcal: Int = computeTotalKcal(foodList)
)

fun getDefaultMeals() = arrayListOf<Meal>(
    Meal(name = Meals.BREAKFAST, day = 0)
)

private fun computeTotalKcal(foodList: List<Food>): Int {
    var totalKcal = 0
    for (food in foodList) {
        totalKcal += food.kcal
    }
    return totalKcal
}
