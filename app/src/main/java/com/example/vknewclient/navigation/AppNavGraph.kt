package com.example.vknewclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vknewclient.domain.FeedPost

/**
 *  Граф навигации для всего приложения
 *  Передаются в него: хост контроллер, контенты экранов
 */
@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedPostScreenContent: @Composable () -> Unit,
    favoritesScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        // Отдельный граф для экрана HOME, поскольку HOME имеет ещё дочерние экраны
        homeScreenNavGraph(
            newsFeedPostScreenContent = newsFeedPostScreenContent,
            commentsScreenContent = commentsScreenContent
        )
        // Создания направления для экрана FAVORITES
        composable(route = Screen.Favorites.route) {
            favoritesScreenContent()
        }
        // Создания направления для экрана PROFILE
        composable(route = Screen.Profile.route) {
            profileScreenContent()
        }
    }
}