package com.example.vknewclient.ui.comments

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComments
import com.example.vknewclient.ui.bottomAppBar.TopAppBarPost

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    comments: List<PostComments>,
    onBackPressed: () -> Unit
) {
    val listComments =
        Scaffold(
            topBar = {
                TopAppBarPost(feedPost = feedPost, onBackPressed = onBackPressed)
            },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(comments, key = { it.id }) { comment ->
                    CommentCardVK(modifier = Modifier, postComments = comment)
                }
            }
        }
}