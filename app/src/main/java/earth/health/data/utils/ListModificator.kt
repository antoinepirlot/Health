package earth.health.data.utils

import androidx.compose.runtime.mutableStateListOf
import earth.health.data.entity.Food

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