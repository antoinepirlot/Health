package earth.health.data.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.MealFoodCrossRef

data class FoodWithMeals(
    @Embedded
    var food: Food,

    @Relation(
        parentColumn = "food_id",
        entityColumn = "meal_id",
        associateBy = Junction(MealFoodCrossRef::class)
    )
    var meals: List<Meal>
)