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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewclient.NewsFeedViewModel
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.ui.postcard.PostCardVK

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(paddingValues: PaddingValues, onCommentClickListener: (FeedPost) -> Unit) {

    // Создание экземпляра viewModel при помощи библиотеки
    val viewModel: NewsFeedViewModel = viewModel()

    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Loading)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPost(
                posts = currentState.posts,
                viewModel = viewModel,
                paddingValues = paddingValues,
                onCommentClickListener = onCommentClickListener
            )
        }

        NewsFeedScreenState.Loading -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPost(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 8.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = paddingValues.calculateBottomPadding() + 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { feedPost ->
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
                    onCommentClickListener = {
                        onCommentClickListener(feedPost)
                    }
                )
            }
        }
    }
}