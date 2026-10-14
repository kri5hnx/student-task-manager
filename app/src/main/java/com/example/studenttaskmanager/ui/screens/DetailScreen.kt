package com.example.studenttaskmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studenttaskmanager.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(task: Task?, onToggle: () -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (task == null) {
            Text("Task not found", Modifier.padding(padding).padding(16.dp))
        } else {
            Column(
                modifier = Modifier.padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(task.title, style = MaterialTheme.typography.headlineMedium)
                Text(if (task.isDone) "Completed" else "Pending")
                Button(onClick = onToggle) {
                    Text(if (task.isDone) "Mark as pending" else "Mark as done")
                }
            }
        }
    }
}
