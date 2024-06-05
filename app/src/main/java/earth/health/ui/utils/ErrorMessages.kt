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

package earth.health.ui.utils

import android.content.Context
import android.widget.Toast

/**
 * @author Antoine Pirlot on 12/09/2023
 */

/**
 * Show toast on screen with a message or idMessage
 * @throws IllegalArgumentException if idMessage and message are both null
 */
fun showToast(context: Context, idMessage: Int? = null,  message: String? = null) {
    val toast = Toast(context)
    if (idMessage != null) {
        toast.setText(idMessage)
    } else {
        if(message == null) {
            throw IllegalArgumentException("Show toast function must have idMessage or message")
        }
        toast.setText(message)
    }
    toast.show()
}