package com.example.vknewclient.domain

import com.example.vknewclient.R

data class PostComments(
    val id: Int = 0,
    val authorName: String = "Author",
    val avatarId: Int = R.drawable.avatar_author,
    val text: String = "Разнообразный и богатый опыт говорит нам, что выбранный нами инновационный путь создаёт предпосылки для глубокомысленных рассуждений.",
    val time: String = "17:00"
)