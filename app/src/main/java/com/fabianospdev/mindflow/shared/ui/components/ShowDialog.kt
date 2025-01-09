package com.fabianospdev.mindflow.shared.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabianospdev.mindflow.R

@Composable
fun ShowDialog(message: String, gradient: Brush, onDismiss: () -> Unit){
    AlertDialog(
        modifier = Modifier
            .size(width = 300.dp, height = 300.dp)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.alert_clip_rounded_corner_shape)))
            .background(gradient)
            .border(
                BorderStroke(
                    width = dimensionResource(R.dimen.alert_border_border_stroke),
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(22.dp)
            ),
        onDismissRequest = onDismiss,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Something went wrong",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error,
                    fontStyle = FontStyle.Normal
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.alert_border_verticalArrangement))
            ) {
                Text(
                    text = "The following error occurred:",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 18.sp
                )
                Text(
                    text = message,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 22.sp
                )
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Row {
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        modifier = Modifier
                            .width(dimensionResource(R.dimen.button_width_small))
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape)))
                            .background(gradient)
                            .border(
                                BorderStroke(
                                    width = dimensionResource(R.dimen.button_border_size),
                                    color = MaterialTheme.colorScheme.onPrimary
                                ),
                                shape = RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape))
                            ),
                    ) {
                        Text("Ok")
                    }
                }
            }
        },
        tonalElevation = 5.dp,
    )
}