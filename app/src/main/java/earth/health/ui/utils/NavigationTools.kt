package earth.health.ui.utils

import androidx.navigation.NavHostController

fun goBackXTimes(navController: NavHostController, numberOfBack: Int) {
    for (i in 1..numberOfBack) {
        navController.popBackStack()
    }
}