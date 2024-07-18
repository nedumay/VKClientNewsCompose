package com.example.vknewclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.PostComments
import com.example.vknewclient.domain.StatisticItem
import com.example.vknewclient.domain.StatisticType
import com.example.vknewclient.ui.HomeScreenState

class MainViewModel : ViewModel() {

    /**
     * Инициализация списка количество 20 шт
     */
    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(20) {
            add(
                FeedPost(
                    id = it,
                    communityName = "Name community $it",
                    avatar = R.drawable.avatar,
                    timePost = "$it min ago",
                    textPost = "Text post $it",
                    imagePost = R.drawable.picture_post,
                    statisticItem = listOf(
                        StatisticItem(StatisticType.LIKES, it + 3),
                        StatisticItem(StatisticType.SHARES, it + 1),
                        StatisticItem(StatisticType.COMMENTS, it + 22),
                        StatisticItem(StatisticType.VIEWS, it * 18)
                    )
                )
            )
        }
    }

    private val comments = mutableListOf<PostComments>().apply {
        repeat(20) {
            add(
                PostComments(id = it)
            )
        }
    }

    private val initialState = HomeScreenState.Posts(initialList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var savedState: HomeScreenState = initialState


    fun deletePost(feedPost: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val modelList = currentState.posts.toMutableList()
        modelList.remove(feedPost)
        _screenState.value = HomeScreenState.Posts(modelList)
    }


    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val oldPost = currentState.posts.toMutableList()

        val oldStatistic = feedPost.statisticItem
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) oldItem.copy(count = oldItem.count + 1)
                else oldItem
            }
        }

        val newFeedPost = feedPost.copy(statisticItem = newStatistic)
        val newPosts = oldPost.apply {
            replaceAll {
                if (it.id == newFeedPost.id) newFeedPost
                else it
            }
        }
        _screenState.value = HomeScreenState.Posts(newPosts)
    }

    fun showComments(feedPost: FeedPost) {
        savedState = _screenState.value ?: initialState
        _screenState.value = HomeScreenState.Comments(comments = comments, feedPost = feedPost)
    }

    fun closeComments() {
        _screenState.value = savedState
    }

}