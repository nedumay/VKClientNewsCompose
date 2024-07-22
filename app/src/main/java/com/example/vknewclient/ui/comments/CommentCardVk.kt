package com.example.vknewclient.ui.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewclient.domain.PostComments
import com.example.vknewclient.ui.theme.VKNewClientTheme

/**
 * Функция для создания карточки комментария
 */
@Composable
fun CommentCardVK(
    modifier: Modifier,
    postComments: PostComments
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .height(50.dp),
                painter = painterResource(id = postComments.avatarId),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = postComments.authorName + " " + postComments.id)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = postComments.text)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = postComments.time)
            }
        }
    }
}

@Preview
@Composable
fun previewCommentCardVK() {
    VKNewClientTheme(darkTheme = false) {
        CommentCardVK(modifier = Modifier, postComments = PostComments())
    }
}