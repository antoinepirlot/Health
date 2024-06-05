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

package earth.health.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import earth.health.ui.theme.HealthTheme

@Composable
fun Card(modifier: Modifier = Modifier, title: String, text: String, mainAction: () -> Unit, fastAction: () -> Unit) {
    Button(
        onClick = mainAction,
        modifier = modifier.padding(10.dp)
    ) {
        Column {
            Text(text = title, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold))
            Text(text = text, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Light))
        }
        ElevatedButton(onClick = fastAction, modifier = modifier.padding(5.dp)) {
            Text(text = "Touch here")
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    HealthTheme {
        Card(title = "Nourriture", text = "1920/1950 kcal", mainAction = {}, fastAction = {})
    }
}