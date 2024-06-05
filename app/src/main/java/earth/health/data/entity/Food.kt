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

/**
 * @author Antoine Pirlot on 24/07/2023
 */

@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "kcal")
    var kcal: Int = 0,

    @ColumnInfo(name = "lipids")
    var lipids: Double = 0.0,

    @ColumnInfo(name = "saturated_lipids")
    var saturatedLipids: Double = 0.0,

    @ColumnInfo(name = "carbohydrates")
    var carbohydrates: Double = 0.0,

    @ColumnInfo(name = "sugar")
    var sugar: Double = 0.0,

    @ColumnInfo(name = "protein")
    var protein: Double = 0.0,

    @ColumnInfo(name = "alimentary_fiber")
    var alimentaryFiber: Double = 0.0,

    @ColumnInfo(name = "calcium")
    var calcium: Double = 0.0,

)

fun getDefaultFood() = listOf(
    Food(name = "Banana", kcal = 105, lipids = 0.3, saturatedLipids = 0.1, calcium = 6.0, alimentaryFiber = 3.1, protein = 1.2, sugar = 14.4, carbohydrates = 26.9),
)

fun getBlankFood() = Food(name = "")