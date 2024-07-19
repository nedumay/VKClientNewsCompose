package com.example.vknewclient.navigation

sealed class Screen(
    val route: String
) {

    object Favorites : Screen(ROUTE_FAVORITES)
    object Profile : Screen(ROUTE_PROFILE)
    object Home : Screen(ROUTE_HOME)

    object Comments : Screen(ROUTE_COMMENTS)
    object NewsFeed : Screen(ROUTE_NEWS_FEED)

    private companion object {

        const val ROUTE_PROFILE = "profile_screen"
        const val ROUTE_FAVORITES = "favorites_screen"
        const val ROUTE_HOME = "home_screen"

        // Добавляем маршруты для вложенного графа HOME
        const val ROUTE_COMMENTS = "comments_screen"
        const val ROUTE_NEWS_FEED = "news_feed_screen"

    }
}
