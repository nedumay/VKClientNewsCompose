package com.example.vknewclient.navigation

import com.example.vknewclient.domain.FeedPost

sealed class Screen(
    val route: String
) {

    object Favorites : Screen(ROUTE_FAVORITES)
    object Profile : Screen(ROUTE_PROFILE)
    object Home : Screen(ROUTE_HOME)

    object Comments : Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            return "$ROUTE_FOR_ARGS/${feedPost.id}"
            // /${Uri.encode(feedPost.contextText)} --> позволяет передавать аргумент с различными спец символами!!
        }
    }
    object NewsFeed : Screen(ROUTE_NEWS_FEED)

    companion object {

        const val KEY_FEED_POST_ID = "feed_post_id"

        const val ROUTE_PROFILE = "profile_screen"
        const val ROUTE_FAVORITES = "favorites_screen"
        const val ROUTE_HOME = "home_screen"

        // Добавляем маршруты для вложенного графа HOME
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}"
        const val ROUTE_NEWS_FEED = "news_feed_screen"

    }
}
