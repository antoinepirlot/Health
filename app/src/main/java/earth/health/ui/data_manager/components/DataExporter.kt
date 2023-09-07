package earth.health.ui.data_manager.components

import android.content.Context
import android.view.ContextMenu
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
    Button(
        modifier = modifier,
        onClick = { HealthDatabase.exportDatabase(context) }
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