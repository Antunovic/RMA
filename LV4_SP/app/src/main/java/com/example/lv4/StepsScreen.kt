package com.example.lv4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun StepsScreen(navController: NavController) {
    var steps = 0
    Column(
        modifier = Modifier
            .fillMaxSize()  
            .padding(16.dp),  
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {

        Text(text = "Broj koraka:")
        Text(text = "${steps}")
        Button(
            onClick = { navController.navigate("HomeScreen") },
            modifier = Modifier
                .padding(bottom = 16.dp, end = 16.dp)
                .wrapContentSize()
        ) {
            Text("Povratak na pocetni zaslon")
        }
    }
}
