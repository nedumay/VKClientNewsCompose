package com.example.vknewclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedPostScreenContent: @Composable () -> Unit,
    favoritesScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        // Отдельный граф для экрана HOME
        homeScreenNavGraph(
            newsFeedPostScreenContent = newsFeedPostScreenContent,
            commentsScreenContent = commentsScreenContent
        )
        // Для создания направления используется эта функция
        composable(route = Screen.Favorites.route) {
            favoritesScreenContent()
        }
        composable(route = Screen.Profile.route) {
            profileScreenContent()
        }
    }
}