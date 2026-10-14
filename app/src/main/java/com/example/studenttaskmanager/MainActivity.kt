package com.example.studenttaskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studenttaskmanager.data.local.AppDatabase
import com.example.studenttaskmanager.data.repository.TaskRepository
import com.example.studenttaskmanager.ui.navigation.AppNavHost
import com.example.studenttaskmanager.ui.theme.StudentTaskManagerTheme
import com.example.studenttaskmanager.ui.viewmodel.TaskViewModel
import com.example.studenttaskmanager.ui.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentTaskManagerTheme {
                val context = LocalContext.current.applicationContext
                val factory = remember {
                    TaskViewModelFactory(TaskRepository(AppDatabase.get(context).taskDao()))
                }
                val viewModel: TaskViewModel = viewModel(factory = factory)
                AppNavHost(viewModel)
            }
        }
    }
}
