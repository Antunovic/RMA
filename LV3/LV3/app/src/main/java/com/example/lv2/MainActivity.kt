package com.example.lv2

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.lv2.ui.theme.Lv2Theme
import com.example.lv2.ui.theme.Typography
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun UserPreview(name: String, height: Float, weight: Float, modifier: Modifier = Modifier) {



    val db = FirebaseFirestore.getInstance()

    var currentWeight by remember { mutableStateOf(weight) } // Current weight (from parameter)
    var currentHeight by remember { mutableStateOf(height) } // Current height (from parameter)

    var newWeight by remember { mutableStateOf(0f) } // New weight as Float
    var newHeight by remember { mutableStateOf(0f) } // New height as Float

    var weightInput by remember { mutableStateOf("") }
    var heightInput by remember { mutableStateOf("") }

    val bmi =  currentWeight / (currentHeight * currentHeight)
    val formattedBmi = String.format("%.2f", bmi)
    val bmiColor = if (bmi < 20) Color.Green else Color.Red


    Box(modifier = modifier) {
        Text(
            text = "Hello $name!", // Promijenjen tekst na engleski
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        )

        // Column in the center
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Row for weight
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Tvoja tezina:", fontSize = 20.sp) // Promijenjen tekst na engleski
                Text(text = "$currentWeight kg", fontSize = 20.sp)
            }
            // Row for height
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Tvoja visina:", fontSize = 20.sp) // Promijenjen tekst na engleski
                Text(text = "$currentHeight m", fontSize = 20.sp)
            }
            // BMI display
            Text(
                text = "Tvoj BMI", // Promijenjen tekst na engleski
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = formattedBmi,
                fontSize = 40.sp,
                color = bmiColor,
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = weightInput,
                onValueChange = { input -> weightInput = input

                    val newValue = input.toFloatOrNull()

                    if (newValue!= null && newValue >= 0) {
                        newWeight = newValue
                    }
                },
                label = { Text("Unesi novu tezinu (kg)") },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
            )
            // TextField for new height
            TextField(
                value = heightInput,
                onValueChange = { input ->  heightInput = input

                    val newValue = input.toFloatOrNull()

                    if (newValue != null && newValue >= 0) {
                        newHeight = newValue
                    }
                },
                label = { Text("Unesi novu visinu (m)") }, // Promijenjen tekst na engleski
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
            )

            Button(
                onClick = {
                    // Update local values
                    if (newWeight > 0) currentWeight = newWeight
                    if (newHeight > 0) currentHeight = newHeight

                    val docRef = db.collection("BMI").document("9NlYwzeyhFzUp5RAluAp")
                    docRef.update(
                        mapOf(
                            "Tezina" to newWeight,
                            "Visina" to newHeight
                        )
                    )
                },
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text("Spremi promjene")
            }



        }
    }
}

@Composable
fun BackgroundImage() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.androidparty),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        UserPreview(name = "Miljenko", height = 1.91f, weight = 60f, modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Lv2Theme {
        BackgroundImage()
    }
}

@Composable
fun ModifierExample() {
    Text(
        text = "Primjer modifikatora",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.LightGray)
    )
}