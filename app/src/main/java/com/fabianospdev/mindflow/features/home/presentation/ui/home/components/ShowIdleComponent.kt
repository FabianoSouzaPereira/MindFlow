package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.features.home.presentation.ui.home.PrivacyPolicySection

@Composable
fun ShowIdleComponent(paddingValues: PaddingValues) {
    val gradient2 = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
            MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.1f),
        ),
        start = Offset(0f, 0f),
        end = Offset(600f, 600f),
        tileMode = TileMode.Clamp
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        color = Color.Transparent,
        contentColor = Color.Transparent
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .background(color = Color.Transparent)
                .padding(
                    top = 80.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(color = Color.Transparent)
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        start = 16.dp,
                        end = 16.dp
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(color = Color.Transparent)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Bem-vindo(a) ao MindFlow",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    FeatureCard(
                        title = "Registro Emocional",
                        description = "Selecione suas emoções e registre seus pensamentos.",
                        iconRes = R.drawable.mood_48dp,
                        iconColor = MaterialTheme.colorScheme.primary,
                        gradient = gradient2,
                        onClick = { /* Navegar para a tela de Registro Emocional */ }
                    )
                    FeatureCard(
                        title = "Exercícios Guiados",
                        description = "Meditação, respiração e outros exercícios.",
                        iconRes = R.drawable.exercise_48dp,
                        iconColor = MaterialTheme.colorScheme.primary,
                        gradient = gradient2,
                        onClick = { /* Navegar para a tela de Exercícios Guiados */ }
                    )
                    FeatureCard(
                        title = "Conteúdo Educativo",
                        description = "Artigos e dicas sobre saúde mental.",
                        iconRes = R.drawable.school_48dp,
                        iconColor = MaterialTheme.colorScheme.primary,
                        gradient = gradient2,
                        onClick = { /* Navegar para a tela de Conteúdo Educativo */ }
                    )
                    PrivacyPolicySection(onPrivacyClick = { /* Mostrar Política de Privacidade */ })
                }
            }
        }
    }
}