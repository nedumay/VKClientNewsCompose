package com.example.vknewclient.ui.postcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Send
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
import com.example.vknewclient.ui.theme.VKNewClientTheme

@Composable
fun PostCardVK() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            HeaderPost()
            Spacer(modifier = Modifier.height(8.dp))
            ImagePost()
            Spacer(modifier = Modifier.height(8.dp))
            BottomPost()
        }
    }
}

@Composable
private fun HeaderPost(){
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
            painter = painterResource(id = R.drawable.avatar),
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
                text = "Name",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "18:30",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary

        )
    }
}

@Composable
private fun ImagePost(){
    Text(
        text = "Здесь название поста, очень длинное название",
        modifier = Modifier
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Image(
        painter = painterResource(id = R.drawable.picture_post),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun BottomPost(){
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
            Text(
                text = "255",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "267",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Rounded.Send,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "13",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.chat_bubble_outline),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "459",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun PreviewLight(){
    VKNewClientTheme(darkTheme = false) {
        PostCardVK()
    }
}

@Preview
@Composable
fun PreviewDark(){
    VKNewClientTheme(darkTheme = true) {
        PostCardVK()
    }
}