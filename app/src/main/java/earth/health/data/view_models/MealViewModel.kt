package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Meal
import earth.health.data.entity.getBlankMeal
import earth.health.data.entity.relations.MealWithFoods
import earth.health.data.entity.relations.getBlankMealsWithFoods
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {
    val mealWithFoodsList = mutableStateListOf<MealWithFoods>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()

    init {
        viewModelScope.launch {
            mealWithFoodsList.addAll(mealDAO.getAll())
        }
    }

    fun readMealWithFoods(mealId: Long): MutableState<MealWithFoods> {
        val mealWithFood = mutableStateOf(getBlankMealsWithFoods())
        viewModelScope.launch {
            mealWithFood.value = mealDAO.getOneWithFoods(mealId = mealId)
        }
        return mealWithFood
    }
    
    fun readMeal(mealId: Long): MutableState<Meal> {
        val meal = mutableStateOf(getBlankMeal())
        viewModelScope.launch {
            meal.value = mealDAO.getOne(mealId = mealId)
        }
        return meal
    }

    fun reloadAll() {
        viewModelScope.launch {
            val dbMealList = mealDAO.getAll()
            mealWithFoodsList.clear()
            mealWithFoodsList.addAll(mealDAO.getAll())
        }
    }


    fun create(meal: Meal) {
        viewModelScope.launch {
            mealDAO.upsert(meal = meal)
            mealWithFoodsList.add(MealWithFoods(meal, mutableStateListOf()))
        }
    }
}