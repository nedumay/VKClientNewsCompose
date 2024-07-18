package com.example.vknewclient.ui.bottomAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.vknewclient.domain.FeedPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarPost(
    feedPost: FeedPost,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Comments for FeedPost id: ${feedPost.id}", fontSize = 22.sp) },
        navigationIcon = {
            IconButton(onClick = { }) {
                IconButton(onClick = {
                    onBackPressed()
                })
                {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )

    )
}

@Preview
@Composable
fun PreviewTopAppBarPost() {
    TopAppBarPost(FeedPost(), { })
}
