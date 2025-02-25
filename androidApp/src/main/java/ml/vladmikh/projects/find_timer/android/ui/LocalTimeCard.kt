package ml.vladmikh.projects.find_timer.android.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ml.vladmikh.projects.find_timer.android.R
import java.time.LocalDateTime

@Composable
fun LocalTimeCard(city :String, time: String, date: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Black)

        ) {
            Box(
                Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                    .padding(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {

                    Column(horizontalAlignment = Alignment.Start) {

                        Spacer(modifier = Modifier.weight(1.0f))

                        Text(
                            stringResource(R.string.your_location),
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(city,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall)

                        Spacer(Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.weight(1.0f))

                    Column(horizontalAlignment = Alignment.End) {

                        Spacer(modifier = Modifier.weight(1.0f))

                        Text(time,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall)

                        Spacer(Modifier.height(8.dp))

                        Text(date,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall)

                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun LocalDateTimePreview() {
    LocalTimeCard("Minsk", "08:00", "Monday 8.sep.2025")
}