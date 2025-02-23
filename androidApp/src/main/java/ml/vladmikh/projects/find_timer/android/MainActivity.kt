package ml.vladmikh.projects.find_timer.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ml.vladmikh.projects.find_timer.android.ui.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen{
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
                    title = {
                        when(it) {
                            0 -> Text(text = "World clocks")
                            else -> Text(text = "Find meetings")
                        }
                    }

                )
            }

        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    MainScreen(){
    }
}
