package com.example.vknewclient.navigation

import android.net.Uri
import com.example.vknewclient.domain.FeedPost
import com.google.gson.Gson

/**
 * Класс перечисления экранов для навигации
 */
sealed class Screen(
    val route: String
) {

    object Favorites : Screen(ROUTE_FAVORITES)
    object Profile : Screen(ROUTE_PROFILE)
    object Home : Screen(ROUTE_HOME)

    object Comments : Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            // Преобразуем в JSON
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
            // /${Uri.encode(feedPost.contextText)} --> позволяет передавать аргумент с различными спец символами!!
        }
    }
    object NewsFeed : Screen(ROUTE_NEWS_FEED)

    companion object {

        const val KEY_FEED_POST = "feed_post"

        const val ROUTE_PROFILE = "profile_screen"
        const val ROUTE_FAVORITES = "favorites_screen"
        const val ROUTE_HOME = "home_screen"

        // Добавляем маршруты для вложенного графа HOME
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS_FEED = "news_feed_screen"

    }

    // Можем создать свою экстеншен функцию с декодированием аргументов
    fun String.encode(): String {
        return Uri.encode(this)
    }
}
