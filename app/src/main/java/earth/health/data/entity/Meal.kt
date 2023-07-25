package earth.health.data.entity

import androidx.compose.ui.res.stringResource
import androidx.room.Entity
import androidx.room.PrimaryKey
import earth.health.R
import java.time.LocalDateTime

@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
//    var foodList: List<Food>?,
//    var date: LocalDateTime = LocalDateTime.now(),
)

val defaultMeals = listOf<Meal>(
    Meal(name = "Breakfast")
)
