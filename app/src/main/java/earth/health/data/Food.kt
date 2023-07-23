package earth.health.data

data class Food(
    val id: Int,
    val name: String,
    val kcal: Int = 0,
    val lipids: Double = 0.0,
    val saturated_lipids: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val sugar: Double = 0.0,
    val protein: Double = 0.0,
    val alimentary_fiber: Double = 0.0,
    val calcium: Double = 0.0
)
