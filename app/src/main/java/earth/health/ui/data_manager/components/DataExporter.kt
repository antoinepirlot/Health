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
import androidx.core.net.toFile
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.DataExporterViewModel
import earth.health.data.HealthDatabase

@Composable
fun DataExporter(
    modifier: Modifier,
) {
    val dataExporterViewModel = viewModel<DataExporterViewModel>()
    val context = LocalContext.current
    val pickDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("Documents/*"),
        onResult = {
            if (it != null) {
                HealthDatabase.exportDatabase(context = context, path = it.path!!)
            }
        }
    )
    Button(
        modifier = modifier,
        onClick = {
            pickDocumentLauncher.launch("health.db")
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