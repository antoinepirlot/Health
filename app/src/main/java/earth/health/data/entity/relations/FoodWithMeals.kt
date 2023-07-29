package earth.health.data.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import earth.health.data.entity.Food
import earth.health.data.entity.Meal

data class FoodWithMeals (
    @Embedded
    val food: Food,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(MealFoodRel::class)
    )
    val meals: List<Meal>
)