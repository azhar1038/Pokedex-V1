package com.az.learncompose.pokedex.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.az.learncompose.pokedex.ui.view.pokemondetail.PokemonDetailView
import com.az.learncompose.pokedex.ui.view.pokemonlist.PokemonListView

@Composable
fun Pokedex(){
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "pokemon_list",
    ) {
        composable("pokemon_list"){
            PokemonListView(navController)
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
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            PokemonDetailView(
                dominantColor = dominantColor,
                pokemonName = pokemonName?.lowercase() ?: "",
                navController = navController,
            )
        }
    }
}