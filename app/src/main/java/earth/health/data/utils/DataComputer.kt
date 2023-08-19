package earth.health.data.utils

import earth.health.data.entity.MealFoodCrossRef
import earth.health.data.entity.relations.MealWithFoods

/**
 * Returns the total kcal of all food in foodList time by its quantity for each relation
 */
fun getTotalKcal(mealWithFoods: MealWithFoods, mealFoodCrossRef: List<MealFoodCrossRef>): Int {
    var totalKcal = 0
    for (food in mealWithFoods.foods) {
        val quantity = mealFoodCrossRef.first {
            it.foodId == food.id && it.mealId == mealWithFoods.meal.id
        }.quantity
        totalKcal += (food.kcal.toDouble() * quantity).toInt()
    }
    return totalKcal
}