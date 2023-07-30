package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "meals_foods_cross_ref",
    primaryKeys = ["meal_id", "food_id"]
)
data class MealFoodCrossRef(
    @ColumnInfo(name  = "meal_id")
    var mealId: Long,
    @ColumnInfo(name = "food_id")
    var foodId: Long,
)
