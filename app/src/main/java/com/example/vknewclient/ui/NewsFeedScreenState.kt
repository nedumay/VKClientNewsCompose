package com.example.vknewclient.ui

import com.example.vknewclient.domain.FeedPost

/**
 * Класс перечисления для состояние экрана новостей
 * В него входит: загрузка, ошибки, данные
 */
sealed class NewsFeedScreenState {

    // Сотояние экрана -> загрузка
    object Loading : NewsFeedScreenState()

    // Состояние экрана -> ошибки
    object Error : NewsFeedScreenState()

    // Состояние экрана -> получение данных (прилетает список постов)
    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()

}