package com.example.vknewclient.ui.comments

import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComments

/**
 * Класс состояния экрана для комментариев: обычно используется
 * Loading -> выводим какое-нибудь экран загрузки,
 * Error -> выводим диалоговое окно с ошибкой,
 * CompleteLoading -> выводим загруженный экран с данными
 */
sealed class CommentsScreenState {
    object Loading : CommentsScreenState()
    data class Comments(
        val comments: List<PostComments>,
        val feedPost: FeedPost
    ) : CommentsScreenState()
}