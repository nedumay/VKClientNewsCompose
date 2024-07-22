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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComments
import com.example.vknewclient.ui.bottomAppBar.TopAppBarPost

/**
 * Функция для создания экрана комментариев
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommentsScreen(
    onBackPressed: () -> Unit,
    feedPost: FeedPost
) {
    /**
     * Т.к. передаем feedPost во ViewModel, то необходимо создать ViewModalFactory и передать во viewModel()
     */
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPost)
    )
    // Создаем стейт скрина с запоминанием его состояния
    val stateScreen = viewModel.screenState.observeAsState(CommentsScreenState.Loading)
    // Создаем экземпляр currentState, чтоб в дальнейшем мы могли точно обращаться к одному и тому же состоянию
    val currentState = stateScreen.value
    // Проверка стейта
    if (currentState is CommentsScreenState.Comments) {
        CommentsScreenContent(
            feedPost = currentState.feedPost,
            comments = currentState.comments,
            onBackPressed = onBackPressed
        )
    }

}

@Composable
fun CommentsScreenContent(
    feedPost: FeedPost,
    comments: List<PostComments>,
    onBackPressed: () -> Unit
) {
        Scaffold(
            topBar = {
                TopAppBarPost(
                    feedPost = feedPost,
                    onBackPressed = onBackPressed
                )
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