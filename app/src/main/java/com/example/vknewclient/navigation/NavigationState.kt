package com.example.vknewclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Вся навигация вынесена из VKNewsMainScreen.kt сюда
 */

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {

        navHostController.navigate(route) {
            // Удаляет в бекстеке все скрины до домашнего экрана (сейчас работате до вложенного графа)
            popUpTo(navHostController.graph.findStartDestination().id) {
                // Сохраняет стейты для всех удаленных скринов
                saveState = true
            }
            // Данная строка не позволит создавать куча одинаковых скринов, в бекстеке только один экран может быть открыт
            launchSingleTop = true
            // Настройка для возвращения стейта
            restoreState = true

        }
    }

    // Функция для перехода на экран комментариев (пользоволяет не выходить из него, при переходах на табах)
    fun navigateToComments() {
        navHostController.navigate(Screen.Comments.route)
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