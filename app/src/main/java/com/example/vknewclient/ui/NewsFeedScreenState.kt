package com.example.vknewclient.ui

import com.example.vknewclient.domain.FeedPost

sealed class NewsFeedScreenState {

    object Loading : NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}