package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {
    val mealWithFoodsList = mutableStateListOf<MealWithFoods>()
    val mealList = mutableStateListOf<Meal>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()

    init {
        reloadAll()
    }

    fun readMealWithFoods(id: Long) = mealWithFoodsList.first { it.meal.id == id }
    
    fun readMeal(mealId: Long) = mealList.first { it.id == mealId }

    fun reloadAll() {
        viewModelScope.launch {
            val dbMealList = mealDAO.getAll()
            mealWithFoodsList.clear()
            mealList.clear()
            for (mealWithFoods in dbMealList) {
                mealList.add(mealWithFoods.meal)
            }
            mealWithFoodsList.addAll(mealDAO.getAll())
        }
    }


    fun create(meal: Meal) {
        viewModelScope.launch {
            mealDAO.insert(meal = meal)
            mealWithFoodsList.add(MealWithFoods(meal, mutableStateListOf()))
            mealList.add(meal)
        }
    }
}