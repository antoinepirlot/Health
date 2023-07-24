package earth.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import earth.health.router.Router
import earth.health.ui.theme.HealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {
                Router()
            }
        }
    }
}