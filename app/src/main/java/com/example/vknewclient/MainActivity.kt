package com.example.vknewclient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import com.example.vknewclient.ui.bottomAppBar.TopAppBarPost
import com.example.vknewclient.ui.mainScreen.MainScreen
import com.example.vknewclient.ui.postcard.PostCardVK
import com.example.vknewclient.ui.theme.VKNewClientTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKNewClientTheme {
                MainScreen()
            }
        }
    }
}

