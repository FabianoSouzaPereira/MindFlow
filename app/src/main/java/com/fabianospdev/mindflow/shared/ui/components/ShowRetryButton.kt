package com.fabianospdev.mindflow.shared.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.core.helpers.CleanableViewModel
import com.fabianospdev.mindflow.core.utils.LoadFontsFamily

@Composable
fun ShowRetryButton(
    viewModel: CleanableViewModel,
    errorMessage: String,
    gradient: Brush,
    onRetry: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color =  MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(16.dp)
            ) {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onTertiary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.something_wrong),
                            fontSize = with(LocalDensity.current) {
                                dimensionResource(id = R.dimen.title_font_text_size).value.sp
                            },
                            fontFamily = LoadFontsFamily.karlaFamily,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onError,
                            fontStyle = FontStyle.Normal,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = stringResource(R.string.the_problem_reported_was, errorMessage))
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = stringResource(R.string.please_try_again_later_but_if_the_problem_persists_report_the_problem_to_your_service_provider))
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .height(80.dp)
            ) {
                Button(
                    onClick = onRetry,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_width_medium))
                        .align(Alignment.BottomCenter)
                        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 0.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape)))
                        .background(MaterialTheme.colorScheme.primary)
                        .border(
                            BorderStroke(
                                width = dimensionResource(R.dimen.button_border_size),
                                color = MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape))
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = stringResource(R.string.try_again),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 50.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(80.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.clearInputFields()
                        viewModel.resetState()
                    },
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_width_medium))
                        .align(Alignment.BottomCenter)
                        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 16.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape)))
                        .background(MaterialTheme.colorScheme.secondary)
                        .border(
                            BorderStroke(
                                width = dimensionResource(R.dimen.button_border_size),
                                color = MaterialTheme.colorScheme.onSecondary
                            ),
                            shape = RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape))
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(
                        text = "Cancel",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
