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

package earth.health.data.utils

import androidx.compose.runtime.mutableStateListOf

/**
 * Easy way to add one element to not mutable list
 */
fun <T>addElement(list: List<T>, element: T): List<T> {
    val tempList = mutableStateListOf<T>()
    tempList.addAll(list)
    tempList.add(element)
    return tempList.toList()
}

/**
 * Easy way to remove one element from not mutable list
 */
fun <T>removeElement(list: List<T>, element: T): List<T> {
    val tempList = mutableStateListOf<T>()
    tempList.addAll(list)
    tempList.remove(element)
    return tempList.toList()
}