package earth.health.data.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import earth.health.data.entity.Day
import earth.health.data.entity.Meal

data class DayWithMeals (
    @Embedded
    val day: Day,
    @Relation(parentColumn = "day_id", entityColumn = "day_id")
    val meals: List<Meal>,
)