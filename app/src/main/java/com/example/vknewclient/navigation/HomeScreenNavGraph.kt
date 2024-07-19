package com.example.vknewclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation


/**
 *  Граф навигации для экрана Home
 *  Создается отдельно, посколько явялется вложенным
 */

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedPostScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(route = Screen.NewsFeed.route) {
            newsFeedPostScreenContent()
        }
        composable(Screen.Comments.route) {
            commentsScreenContent()
        }
    }
}
