package com.example.vknewclient.ui.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewclient.MainViewModel
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.ui.bottomAppBar.TopAppBarPost
import com.example.vknewclient.ui.postcard.PostCardVK

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)


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
            val feedPost = viewModel.feedPost.observeAsState(FeedPost())
            Column {
                Spacer(modifier = Modifier.padding(it))
                PostCardVK(
                    modifier = Modifier.padding(8.dp),
                    feedPost = feedPost.value,
                    onLikeClickListener = viewModel::updateCount, // Тоеже самое что и ниже !
                    onShareClickListener = {viewModel.updateCount(it) },
                    onViewsClickListener = {viewModel.updateCount(it) },
                    onCommentClickListener = {viewModel.updateCount(it) }
                )
            }

        }
    }
}
