package earth.health.data.entity

import androidx.compose.ui.res.stringResource
import earth.health.R

enum class Meals(val mealNameId: Int) {
    BREAKFAST(R.string.breakfast),
    LUNCH(R.string.lunch),
    DINNER(R.string.dinner),
    EXTRAS(R.string.extras)
}