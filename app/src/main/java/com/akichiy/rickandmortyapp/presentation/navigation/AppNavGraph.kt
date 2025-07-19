package com.akichiy.rickandmortyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akichiy.rickandmortyapp.presentation.character_detail_screen.CharacterDetailScreen
import com.akichiy.rickandmortyapp.presentation.character_list_screen.CharacterListScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "character_list") {
        composable("character_list") {
            CharacterListScreen(navController)
        }
        composable(
            "character_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            CharacterDetailScreen(characterId = id, navController = navController)
        }
    }
}