package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["food_id"]),
        Index(value = ["meal_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Meal::class,
            parentColumns = ["id"],
            childColumns = ["meal_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FoodMealRel(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "food_id") var foodId: Int,
    @ColumnInfo(name = "meal_id") var mealId: Int,
)