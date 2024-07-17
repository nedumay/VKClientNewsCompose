package com.example.vknewclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.StatisticItem

class MainViewModel : ViewModel() {

    private val _feedPost = MutableLiveData<FeedPost>(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    fun updateCount(item: StatisticItem){
        val oldStatistic = feedPost.value?.statisticItem ?: throw IllegalStateException("No statistic")
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) oldItem.copy(count = oldItem.count + 1)
                else oldItem
            }
        }
        _feedPost.value = feedPost.value?.copy(statisticItem = newStatistic) ?: throw IllegalStateException("No statistic")
    }

}