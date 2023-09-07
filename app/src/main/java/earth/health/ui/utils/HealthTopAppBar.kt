package earth.health.ui.utils

import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthTopAppBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.app_name)
            )
        }
    )
}

@Preview
@Composable
fun HealthTopAppBarPreview() {
    HealthTopAppBar()
}