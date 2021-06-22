package com.az.learncompose.pokedex.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Pokedex(){
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "pokemon_list",
    ) {
        composable("pokemon_list"){
            Text("Pokemon List")
        }
        composable(
            route = "pokemon_detail/{dominantColor}/{pokemonName}",
            arguments = listOf(
                navArgument("dominantColor"){
                    type = NavType.IntType
                },
                navArgument("pokemonName"){
                    type = NavType.StringType
                }
            )
        ){
            Text("Pokemon Detail")
        }
    }
}