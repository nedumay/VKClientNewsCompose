package com.example.vknewclient.ui.postcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewclient.R
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.domain.StatisticItem
import com.example.vknewclient.domain.StatisticType
import com.example.vknewclient.ui.theme.VKNewClientTheme

/**
 * Функция для создания карточки поста
 */

@Composable
fun PostCardVK(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            HeaderPost(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            ImagePost(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            BottomPost(
                statistics = feedPost.statisticItem,
                onLikeClickListener = onLikeClickListener,
                onViewsClickListener = onViewsClickListener,
                onShareClickListener = onShareClickListener,
                onCommentClickListener = onCommentClickListener
            )
        }
    }
}

/**
 * Функция для создания шапки поста
 */
@Composable
private fun HeaderPost(feedPost: FeedPost){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                ),
            painter = painterResource(id = feedPost.avatar),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
        ){
            Text(
                text = feedPost.communityName,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.timePost,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimaryContainer

        )
    }
}

/**
 * Функция для создания изображения и текста поста
 */
@Composable
private fun ImagePost(feedPost: FeedPost){
    Text(
        text = feedPost.textPost,
        modifier = Modifier
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Image(
        painter = painterResource(id = feedPost.imagePost),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        contentScale = ContentScale.Crop
    )
}

/**
 * Функция для создания нижней части поста
 */
@Composable
private fun BottomPost(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        Row(

            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)

        ) {
            val viewItem = statistics.getItemByType(StatisticType.VIEWS)
            Text(
                text = viewItem.count.toString(),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .clickable {
                        onViewsClickListener(viewItem)
                    }

            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val shareItem = statistics.getItemByType(StatisticType.SHARES)
            Text(
                text = shareItem.count.toString(),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Send,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .clickable {
                        onShareClickListener(shareItem)
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            Text(
                text = commentsItem.count.toString(),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.chat_bubble_outline),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .clickable {
                        onCommentClickListener(commentsItem)
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            Text(
                text = likesItem.count.toString(),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .clickable {
                        onLikeClickListener(likesItem)
                    }
            )
        }
    }
}

/**
 * Экстеншен функция для получения статистики по типу: просмотры, лайки и т.д.
 */
private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalArgumentException("No item with type $type")
}

@Preview
@Composable
fun PreviewLight(){
    VKNewClientTheme(darkTheme = false) {
        PostCardVK(feedPost = FeedPost(), onLikeClickListener = {}, onShareClickListener = {}, onViewsClickListener = {}, onCommentClickListener = {})
    }
}

@Preview
@Composable
fun PreviewDark(){
    VKNewClientTheme(darkTheme = true) {
        PostCardVK(feedPost = FeedPost(), onLikeClickListener = {}, onShareClickListener = {}, onViewsClickListener = {}, onCommentClickListener = {})
    }
}