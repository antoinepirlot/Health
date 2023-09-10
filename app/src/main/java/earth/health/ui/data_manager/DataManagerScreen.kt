package earth.health.ui.data_manager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import earth.health.ui.data_manager.components.DataExporter
import earth.health.ui.data_manager.components.DataImporter

@Composable
fun DataManagerScreen(
    modifier: Modifier,
) {
    Row {
        DataExporter(modifier = modifier)
        DataImporter(modifier = modifier)
    }
}

@Preview
@Composable
fun DataManagerPreview(){
    DataManagerScreen(
        modifier = Modifier,
    )
}