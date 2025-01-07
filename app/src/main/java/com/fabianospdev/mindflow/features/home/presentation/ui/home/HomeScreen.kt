package com.fabianospdev.mindflow.features.home.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.FeatureCard
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.HomeScreenAppBar
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState
import com.fabianospdev.mindflow.features.home.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    name: String
) {

    val state by viewModel.state.observeAsState(HomeState.HomeIdle)
    val context = LocalContext.current
    Scaffold(
        topBar = {
            HomeScreenAppBar()
        }
    ) { paddingValues ->

        when (state) {
            is HomeState.HomeLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeState.HomeIdle -> {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Bem-vindo(a), $name",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FeatureCard(
                            title = "Registro Emocional",
                            description = "Selecione suas emoções e registre seus pensamentos.",
                            iconRes = R.drawable.mood_48dp,
                            iconColor = MaterialTheme.colorScheme.primary,
                            onClick = { /* Navegar para a tela de Registro Emocional */ }
                        )
                        FeatureCard(
                            title = "Exercícios Guiados",
                            description = "Meditação, respiração e outros exercícios.",
                            iconRes = R.drawable.exercise_48dp,
                            iconColor = MaterialTheme.colorScheme.primary,
                            onClick = { /* Navegar para a tela de Exercícios Guiados */ }
                        )
                        FeatureCard(
                            title = "Conteúdo Educativo",
                            description = "Artigos e dicas sobre saúde mental.",
                            iconRes = R.drawable.school_48dp,
                            iconColor = MaterialTheme.colorScheme.primary,
                            onClick = { /* Navegar para a tela de Conteúdo Educativo */ }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        PrivacyPolicySection(onPrivacyClick = { /* Mostrar Política de Privacidade */ })
                    }
                }
            }

            is HomeState.HomeSuccess -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("HomeSuccess")
                }
            }

            is HomeState.HomeError -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("HomeError")
                }
            }

            is HomeState.HomeNoConnection -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("HomeNoConnection")
                }
            }

            is HomeState.HomeTimeoutError -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("HomeTimeoutError")
                }
            }

            is HomeState.HomeUnauthorized -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("HomeUnauthorized")
                }
            }

            is HomeState.HomeValidationError -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("HomeValidationError")
                }
            }

            else -> {
                HomeState.HomeUnknown("Error State Unknown")
            }
        }
    }
}

//@Composable
//fun FeatureCard(title: String, description: String, iconRes: Int, iconColor: Color, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable(onClick = onClick),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = iconRes),
//                contentDescription = null,
//                modifier = Modifier.size(48.dp)
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                Text(text = title, style = MaterialTheme.typography.labelSmall)
//                Text(text = description, style = MaterialTheme.typography.labelSmall)
//            }
//        }
//    }
//}

@Composable
fun PrivacyPolicySection(onPrivacyClick: () -> Unit) {
    Text(
        text = "Política de Privacidade",
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onPrivacyClick)
    )
}