package earth.health.data.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.MealFoodCrossRef
import earth.health.data.entity.getBlankMeal

data class MealWithFoods(
    @Embedded
    var meal: Meal,

    @Relation(
        parentColumn = "meal_id",
        entityColumn = "food_id",
        associateBy = Junction(MealFoodCrossRef::class)
    )
    var foods: List<Food>
)

fun getBlankMealsWithFoods() = MealWithFoods(getBlankMeal(), listOf())
