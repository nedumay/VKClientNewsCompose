package com.example.vknewclient.ui

import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComments

sealed class HomeScreenState {

    object Loading : HomeScreenState()
    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComments>) :
        HomeScreenState()
}