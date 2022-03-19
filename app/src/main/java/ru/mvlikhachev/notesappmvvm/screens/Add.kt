package ru.mvlikhachev.notesappmvvm.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.mvlikhachev.notesappmvvm.navigation.NavRoute

@Composable
fun AddScreen(navController: NavHostController) {
    Log.d("checkData", "navigator: ${navController.currentBackStackEntry?.destination?.route}")
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add new Note",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Note title") }
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text("Note subtitle") }
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(NavRoute.Main.route)
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}