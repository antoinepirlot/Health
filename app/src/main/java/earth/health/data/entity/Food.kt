package earth.health.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey (autoGenerate = true) var id: Long = 0,
    var name: String,
    var kcal: Int = 0,
    var lipids: Double = 0.0,
    var saturated_lipids: Double = 0.0,
    var carbohydrates: Double = 0.0,
    var sugar: Double = 0.0,
    var protein: Double = 0.0,
    var alimentary_fiber: Double = 0.0,
    var calcium: Double = 0.0
)
