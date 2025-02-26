package ml.vladmikh.projects.find_timer.android.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.unit.dp

@Composable
fun NumberTimeCard(label: String, hour: MutableState<Int>) {

    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(16.dp)
    ){
        Row(
            modifier = Modifier
        ) {

            Text(
                label,
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.size(16.dp)
            )

            NumberPicker(
                hour = hour,
                range = IntRange(0, 23),
                onStateChanged = {
                    hour.value = it
                }
            )

        }
    }
}