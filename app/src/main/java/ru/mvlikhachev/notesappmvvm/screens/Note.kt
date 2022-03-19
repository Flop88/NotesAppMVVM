package ru.mvlikhachev.notesappmvvm.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.mvlikhachev.notesappmvvm.ui.theme.NotesAppMVVMTheme

@Composable
fun NoteScreen(navController: NavHostController) {
    Log.d("checkData", "navigator: ${navController.currentBackStackEntry?.destination?.route}")
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 24.dp)
                    .clickable { Log.d("checkData", "Clicked on card item") },
                elevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "title",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "subtitle")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevNoteScreen() {
    NotesAppMVVMTheme {
        NoteScreen(rememberNavController())
    }
}