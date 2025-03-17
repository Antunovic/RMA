package com.example.lv2

import android.os.Bundle
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun UserPreview(name: String, visina: Float, tezina: Float, modifier: Modifier = Modifier) {
    val bmi = tezina / (visina * visina)
    val formattedBmi = String.format("%.2f", bmi)
    val bmiColor = if (bmi < 20) Color.Green else Color.Red

    Box(modifier = modifier) {

        Text(
            text = "Pozdrav Miljenko!",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        )

        // Column u sredini
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Prvi Row za težinu
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Tvoja težina:", fontSize = 20.sp)
                Text(text = "$tezina kg", fontSize = 20.sp)
            }
            // Drugi Row za visinu
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Tvoja visina:", fontSize = 20.sp)
                Text(text = "$visina m", fontSize = 20.sp)
            }
            // Ispis BMI-a
            Text(
                text = "Tvoj BMI",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = formattedBmi,
                fontSize = 40.sp,
                color = bmiColor,
                fontWeight = FontWeight.Bold
            )
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
        UserPreview(name = "Miljenko", visina = 1.91f, tezina = 60f, modifier = Modifier.fillMaxSize())
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