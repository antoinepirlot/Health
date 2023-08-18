package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "kcal")
    var kcal: Int = 0,

    @ColumnInfo(name = "lipids")
    var lipids: Double = 0.0,

    @ColumnInfo(name = "saturated_lipids")
    var saturatedLipids: Double = 0.0,

    @ColumnInfo(name = "carbohydrates")
    var carbohydrates: Double = 0.0,

    @ColumnInfo(name = "sugar")
    var sugar: Double = 0.0,

    @ColumnInfo(name = "protein")
    var protein: Double = 0.0,

    @ColumnInfo(name = "alimentary_fiber")
    var alimentaryFiber: Double = 0.0,

    @ColumnInfo(name = "calcium")
    var calcium: Double = 0.0,

)

fun getDefaultFood() = listOf<Food>(
    Food(name = "Banana", kcal = 105, lipids = 0.3, saturatedLipids = 0.1, calcium = 6.0, alimentaryFiber = 3.1, protein = 1.2, sugar = 14.4, carbohydrates = 26.9),
)