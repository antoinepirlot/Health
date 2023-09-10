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

@Composable
fun DataImporter(
    modifier: Modifier
) {
    val context = LocalContext.current
    //todo add the ability to select file
    val path = "/storage/emulated/0/Documents/health.db"
    val pickPictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            if (it != null) {
                HealthDatabase.importDatabase(context, it.path!!)
            }
        }
    )
    Button(
        modifier = modifier,
        onClick = {
            pickPictureLauncher.launch("health.db")
        }
    ) {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.import_data)
        )
    }
}

@Preview
@Composable
fun DataImporterPreview() {
    DataImporter(modifier = Modifier)
}