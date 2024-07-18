package com.example.vknewclient.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vknewclient.MainViewModel
import com.example.vknewclient.ui.postcard.PostCardVK

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())
    LazyColumn(
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 8.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = paddingValues.calculateBottomPadding() + 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(feedPosts.value, key = { it.id }) { feedPost ->
            /**
             * В новых версихя Compose удаление через SwipeToDismissBox
             */
            /**
             * В новых версихя Compose удаление через SwipeToDismissBox
             */
            val dismissState = rememberSwipeToDismissBoxState()
            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                backgroundContent = {},
                enableDismissFromEndToStart = true,
                enableDismissFromStartToEnd = false
            ) {
                if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                    viewModel.deletePost(feedPost)
                }
                PostCardVK(
                    feedPost = feedPost,
                    onLikeClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    },
                    onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    },
                    onCommentClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    }
                )
            }
        }
    }
}