package com.example.studenttaskmanager.ui.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.studenttaskmanager.ui.screens.AddTaskScreen
import com.example.studenttaskmanager.ui.screens.DetailScreen
import com.example.studenttaskmanager.ui.screens.TaskListScreen
import com.example.studenttaskmanager.ui.viewmodel.TaskViewModel

@Composable
fun AppNavHost(
    viewModel: TaskViewModel,
    navController: NavHostController = rememberNavController()
) {
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            TaskListScreen(
                tasks = tasks,
                onToggle = viewModel::toggle,
                onDelete = viewModel::delete,
                onAddClick = { navController.navigate("add") },
                onTaskClick = { id -> navController.navigate("detail/$id") }
            )
        }
        composable("add") {
            AddTaskScreen(
                onSave = { title ->
                    viewModel.addTask(title)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("taskId") ?: return@composable
            DetailScreen(
                task = tasks.firstOrNull { it.id == id },
                onToggle = { viewModel.toggle(id) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
