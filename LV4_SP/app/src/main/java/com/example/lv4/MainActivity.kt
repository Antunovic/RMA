package com.example.lv4

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lv4.ui.theme.LV4Theme
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LV4Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "HomeScreen") {
                    composable("HomeScreen") {
                        HomeScreen(navController = navController)
                    }
                    composable("stepsScreen") {
                        StepsScreen(navController = navController)
                    }


                }
            }
        }
    }
}

@Composable
fun UserPreview(
    viewModel: BmiViewModel = viewModel(), navController: NavController, name: String, modifier: Modifier = Modifier)
{
    var weightInput by remember { mutableStateOf("") }
    var heightInput by remember { mutableStateOf("") }

    val bmiColor = if (viewModel.bmi.value.toFloatOrNull() ?: 0f < 20) Color.Green else Color.Red

    Box(modifier = modifier) {
        Text(
            text = "Hello $name!",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Tvoja tezina:", fontSize = 20.sp)
                Text(text = "${viewModel.weight.value} kg", fontSize = 20.sp)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Tvoja visina:", fontSize = 20.sp)
                Text(text = "${viewModel.height.value} m", fontSize = 20.sp)
            }
            Text(
                text = "Tvoj BMI",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = viewModel.bmi.value,
                fontSize = 40.sp,
                color = bmiColor,
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = weightInput,
                onValueChange = { weightInput = it },
                label = { Text("Unesi novu tezinu (kg)") },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
            )
            TextField(
                value = heightInput,
                onValueChange = { heightInput = it },
                label = { Text("Unesi novu visinu (m)") },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
            )
            Button(
                onClick = {
                    val newWeight = weightInput.toFloatOrNull()
                    val newHeight = heightInput.toFloatOrNull()
                    viewModel.updateMeasurements(newHeight, newWeight)
                    weightInput = ""
                    heightInput = ""
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Spremi promjene")
            }

            Button(
                onClick = { navController.navigate("stepsScreen") },
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 16.dp)
                    .wrapContentSize()
            ) {
                Text("Pogledaj broj koraka")
            }

        }
    }
}

@Composable
fun BackgroundImage(navController: NavController) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.androidparty),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        UserPreview(name = "Miljenko",navController= navController, modifier = Modifier.fillMaxSize())
    }
}


@Composable
fun HomeScreen(navController: NavController) {
    LV4Theme{
        BackgroundImage(navController)
    }
}

