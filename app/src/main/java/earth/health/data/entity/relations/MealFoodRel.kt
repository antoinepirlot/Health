package earth.health.data.entity.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import earth.health.data.entity.Food

@Entity(
    tableName = "meals_foods_rel",
    foreignKeys = [
        ForeignKey(entity = Food::class, parentColumns = ["id"], childColumns = ["id"]),
        ForeignKey(entity = Food::class, parentColumns = ["id"], childColumns = ["id"])
    ]
)
data class MealFoodRel(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "meal_id")
    var mealId: Long,

    @ColumnInfo(name = "food_id")
    var foodId: Long
)