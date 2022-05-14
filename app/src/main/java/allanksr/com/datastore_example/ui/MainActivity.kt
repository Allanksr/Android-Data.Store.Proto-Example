package allanksr.com.datastore_example.ui

import allanksr.com.datastore_example.ui.nav_graph.NavGraphController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import allanksr.com.datastore_example.ui.theme.DataStoreExampleTheme
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreExampleTheme(
                darkTheme = true,
                content = {
                    Surface(
                        color = MaterialTheme.colors.background,
                        content = {
                            NavGraphController()
                        }
                    )
                }
            )
        }
    }
}
