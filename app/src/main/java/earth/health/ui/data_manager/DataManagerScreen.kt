package earth.health.ui.data_manager

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import earth.health.ui.data_manager.components.DataExporter

@Composable
fun DataManagerScreen(
    modifier: Modifier,
) {
    Column {
        DataExporter(modifier = modifier)
    }
}

@Preview
@Composable
fun DataManagerPreview(){
    DataManagerScreen(
        modifier = Modifier,
    )
}