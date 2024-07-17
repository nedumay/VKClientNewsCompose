@file:Suppress("DEPRECATION")

package com.example.vknewclient.ui.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.vknewclient.MainViewModel
import com.example.vknewclient.ui.bottomAppBar.TopAppBarPost
import com.example.vknewclient.ui.postcard.PostCardVK

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val listItems = listOf(
        NavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        NavItem("Favorite", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
        NavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person),
    )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                listItems.forEachIndexed { index, navItem ->
                    NavigationDrawerItem(
                        label = { Text(text = navItem.title) },
                        selected = selectedItemIndex == index,
                        icon = {
                            Icon(
                                imageVector = if (selectedItemIndex == index) navItem.selectedIcon
                                else navItem.unselectedIcon, contentDescription = navItem.title
                            )
                        },
                        onClick = { selectedItemIndex = index })
                }
            }
        }) {
        Scaffold(
            topBar = {
                TopAppBarPost()
            },
            bottomBar = {
                NavigationBar {
                    listItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = { selectedItemIndex = index },
                            icon = {
                                Icon(
                                    imageVector = if (selectedItemIndex == index) item.selectedIcon
                                    else item.unselectedIcon, contentDescription = item.title
                                )
                            },
                            label = { Text(text = item.title) }
                        )
                    }
                }
            }
        ) {
            val feedPosts = viewModel.feedPosts.observeAsState(listOf())
            LazyColumn (
                contentPadding = PaddingValues(
                    top = it.calculateTopPadding() + 8.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = it.calculateBottomPadding() + 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(feedPosts.value, key = { it.id }) { feedPost ->
                    /**
                     * В новых версихя Compose удаление через SwipeToDismissBox
                     */
                    val dismissState = rememberSwipeToDismissBoxState()
                    SwipeToDismissBox(
                        modifier = Modifier.animateItemPlacement(),
                        state = dismissState,
                        backgroundContent = {},
                        enableDismissFromEndToStart = true,
                        enableDismissFromStartToEnd = false
                    ) {
                        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                            viewModel.deletePost(feedPost)
                        }
                        PostCardVK(
                            feedPost = feedPost,
                            onLikeClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                )
                            },
                            onShareClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                ) },
                            onViewsClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                ) },
                            onCommentClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                )
                            }
                        )
                    }
                }
            }

        }
    }
}
