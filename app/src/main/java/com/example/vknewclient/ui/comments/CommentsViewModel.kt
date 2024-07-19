package com.example.vknewclient.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComments

class CommentsViewModel(feedPost: FeedPost) : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Loading)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost = feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComments>().apply {
            repeat(10) {
                add(PostComments(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(feedPost = feedPost, comments = comments)
    }
}
