/*
 * This file is part of Health.
 *
 *  Health is free software: you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software Foundation,
 *  either version 3 of the License, or (at your option) any later version.
 *
 *  Health is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with Health.
 *  If not, see <https://www.gnu.org/licenses/>.
 *
 *  **** INFORMATIONS ABOUT THE AUTHOR *****
 *  The author of this file is Antoine Pirlot, the owner of this project.
 *  You find this original project on github.
 *
 *  My github link is: https://github.com/antoinepirlot
 *  This current project's link is: https://github.com/antoinepirlot/Health
 *
 *  You can contact me via my email: pirlot.antoine@outlook.com
 *  PS: I don't answer quickly.
 */

package earth.health.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("meal_id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: Meals,

    @ColumnInfo(name = "day_id")
    var dayId: Long,

    @ColumnInfo(name = "totalKcal")
    var totalKcal: Int = 0,

)

fun getDefaultMeals() = arrayListOf(
    Meal(name = Meals.BREAKFAST, dayId = (0).toLong())
)

private fun computeTotalKcal(foodList: List<Food>): Int {
    var totalKcal = 0
    for (food in foodList) {
        totalKcal += food.kcal
    }
    return totalKcal
}

fun getBlankMeal() = Meal(name = Meals.BREAKFAST, dayId = 0)
