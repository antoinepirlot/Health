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

package earth.health.ui.data_manager.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R
import earth.health.data.HealthDatabase
import earth.health.ui.utils.showToast

/**
 * @author Antoine Pirlot on 07/09/2023
 */

@Composable
fun DataExporter(
    modifier: Modifier,
) {
    val context = LocalContext.current
    val documentPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/x-sql")
    ) {
        if (it == null) return@rememberLauncherForActivityResult

        if (HealthDatabase.exportDatabase(context = context, uri = it))
            showToast(context = context, idMessage = R.string.export_passed)
        else
            showToast(context = context, idMessage = R.string.export_failed)
    }
    Button(
        modifier = modifier,
        onClick = {
            documentPicker.launch("health")
        }
    ) {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.export_data)
        )
    }
}

@Preview
@Composable
fun DataExporterPreview() {
    DataExporter(
        modifier = Modifier,
    )
}