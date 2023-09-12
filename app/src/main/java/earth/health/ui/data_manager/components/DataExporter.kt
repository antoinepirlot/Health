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

@Composable
fun DataExporter(
    modifier: Modifier,
) {
    val context = LocalContext.current
    val documentPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("documents/*")
    ) {
        if (it == null) return@rememberLauncherForActivityResult

        HealthDatabase.exportDatabase(context = context, uri = it)
        showToast(context = context, idMessage = R.string.export_passed)
    }
    Button(
        modifier = modifier,
        onClick = {
            documentPicker.launch("health.db")
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