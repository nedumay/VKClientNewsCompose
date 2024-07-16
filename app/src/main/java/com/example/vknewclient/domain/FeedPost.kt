package com.example.vknewclient.domain

import com.example.vknewclient.R

data class FeedPost(
    val communityName: String = "Name",
    val timePost: String = "16:00",
    val textPost: String = "Text",
    val avatar: Int = R.drawable.avatar,
    val imagePost: Int = R.drawable.picture_post,
    val statisticItem: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, count = 123),
        StatisticItem(type = StatisticType.SHARES, count = 8),
        StatisticItem(type = StatisticType.COMMENTS, count = 7),
        StatisticItem(type = StatisticType.LIKES, count = 32),
    )
)
