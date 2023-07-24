package earth.health.data.entity

import java.time.LocalDateTime
import java.util.Date

data class Meal(
    val id: Long,
    val foodList: List<Food>,
    val date: LocalDateTime,
)

val defaultMealList = listOf<Meal>(
    Meal(1, defaultFoodList, LocalDateTime.now())
)
