package ru.mvlikhachev.notesappmvvm.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.mvlikhachev.notesappmvvm.navigation.NavRoute
import ru.mvlikhachev.notesappmvvm.ui.theme.NotesAppMVVMTheme

@Composable
fun StartScreen(navController: NavHostController) {
    Log.d("checkData", "navigator: ${navController.currentBackStackEntry?.destination?.route}")
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What will we use?")
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Main.route)
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Room database")
            }
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Main.route)
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Firebase database")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    NotesAppMVVMTheme {
        StartScreen(rememberNavController())
    }
}