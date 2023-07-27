package earth.health.data.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import earth.health.data.entity.Food
import earth.health.data.entity.Meal

data class MealWithFoods(
    @Embedded val meal: Meal,
    @Relation(parentColumn = "id", entityColumn = "id") val foodList: List<Food>,
)