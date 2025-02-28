package ml.vladmikh.projects.find_timer.android.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ml.vladmikh.projects.find_timer.android.R


@Composable
fun TimeCard( timeZone: String,
              hours: Double,
              time: String,
              date: String
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(128.dp)
        .padding(8.dp)

    ) {

        Card(shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)

            ) {

                Row(modifier = Modifier.fillMaxWidth()) {

                    Column(horizontalAlignment = Alignment.Start) {

                        Text(timeZone,
                            style= TextStyle(color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.weight(1.0f))

                        Row() {

                            Text(hours.toString(),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            )

                            Text(stringResource(R.string.hours_from_local),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            )
                        }

                    }

                    Spacer(modifier = Modifier.weight(1.0f))

                    Column(horizontalAlignment = Alignment.End) {

                        Text(time,
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        )

                        Spacer(modifier = Modifier.weight(1.0f))

                        Text(date,
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }

        }

    }
}
