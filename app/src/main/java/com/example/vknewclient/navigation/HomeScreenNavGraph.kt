package com.example.vknewclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.vknewclient.domain.FeedPost


/**
 *  Граф навигации для экрана Home
 *  Создается отдельно, посколько явялется вложенным
 */

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedPostScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(route = Screen.NewsFeed.route) {
            newsFeedPostScreenContent()
        }
        // Получаем параметры в графе навигации
        composable(
            route = Screen.Comments.route,
            // Данная строка помогает сразу переобразовывать типы. В данном случае из String в Int
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            // arguments передает String
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            commentsScreenContent(FeedPost(id = feedPostId))
        }
    }
}
