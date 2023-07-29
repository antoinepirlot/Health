package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "kcal") var kcal: Int = 0,
    @ColumnInfo(name = "lipids") var lipids: Double = 0.0,
    @ColumnInfo(name = "saturated_lipids") var saturatedLipids: Double = 0.0,
    @ColumnInfo(name = "carbohydrates") var carbohydrates: Double = 0.0,
    @ColumnInfo(name = "sugar") var sugar: Double = 0.0,
    @ColumnInfo(name = "protein") var protein: Double = 0.0,
    @ColumnInfo(name = "alimentary_fiber") var alimentaryFiber: Double = 0.0,
    @ColumnInfo(name = "calcium") var calcium: Double = 0.0,
)

fun getDefaultFood() = listOf<Food>(
    Food(name = "Banana"),
    Food(name = "Pasta"),
    Food(name = "Cherry Tomato"),
)