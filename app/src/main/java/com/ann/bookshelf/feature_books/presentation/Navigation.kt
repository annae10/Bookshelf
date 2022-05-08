package com.ann.bookshelf.feature_books.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ann.bookshelf.feature_books.presentation.edit.EditScreen
import com.ann.bookshelf.feature_books.presentation.home.HomeScreen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Edit.route,
            arguments = listOf(
                navArgument(name = "bookId"){
                    type = NavType.IntType;
                    defaultValue = -1
                }
            )
        ){
            EditScreen(navController = navController)
        }
    }
}