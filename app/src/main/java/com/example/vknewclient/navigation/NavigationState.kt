package com.example.vknewclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Вся навигация вынесена из MainScreen.kt сюда
 */

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {

        navHostController.navigate(route) {
            // Удаляет в бекстеке все скрины до домашнего экрана
            popUpTo(navHostController.graph.startDestinationId) {
                // Сохраняет стейты для всех удаленных скринов
                saveState = true
            }
            // Данная строка не позволит создавать куча одинаковых скринов, в бекстеке только один экран может быть открыт
            launchSingleTop = true
            // Настройка для возвращения стейта
            restoreState = true

        }
    }
}

// Данная реализация позволит не создавать NavigationState в файле экрана
@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}