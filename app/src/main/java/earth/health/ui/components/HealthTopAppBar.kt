package earth.health.ui.components
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import earth.health.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthTopAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.app_name)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (!isHomePage(navController))
                    navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Preview
@Composable
fun HealthTopAppBarPreview() {
    HealthTopAppBar(
        navController = rememberNavController(),
    )
}

private fun isHomePage(navController: NavHostController): Boolean {
    val currentDestination = navController.currentDestination
    return currentDestination == null || currentDestination.route == "/"
}