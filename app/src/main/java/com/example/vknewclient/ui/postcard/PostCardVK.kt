package com.example.vknewclient.ui.postcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Preview(showBackground = true)
@Composable
fun PostCardVK() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(600.dp)


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
            Column (
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(50.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.Center,
            ){
                Text(
                    text = "Name",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "18:30",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "",

            )
        }
        Text(
            text = "Здесь название поста, очень длинное название",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.picture_post),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(3f)
            ){
                Text(
                    text = "255",
                )
                Image (
                    modifier = Modifier
                        .padding(start = 4.dp),
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = ""
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = "267",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Image (
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = ""
                )
                Text(
                    text = "13",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Image (
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = ""
                )
                Text(
                    text = "459",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Image (
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = ""
                )
            }
        }
    }
}