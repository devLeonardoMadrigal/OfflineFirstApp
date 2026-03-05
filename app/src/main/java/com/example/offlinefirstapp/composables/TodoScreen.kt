package com.example.offlinefirstapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.offlinefirstapp.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel = hiltViewModel()) {

    var textField by remember { mutableStateOf("") }
    val currentItems by viewModel.items.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().safeContentPadding() ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = textField,
                onValueChange = {textField = it},
                label = { Text("Write a new TODO") }
            )

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(modifier = Modifier.fillMaxWidth(),onClick = {
                viewModel.addItem(
                    text = textField
                )
            }) { Text("Add item")}
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = currentItems){ item ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        var isChecked by remember { mutableStateOf(item.isCompleted) }
                        Text(item.text)
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = {
                                checkBoxState -> isChecked = checkBoxState
                                viewModel.updateCompletedStatus(item, newStatus = isChecked)
                            },
                        )

                    }
                }
            }
        }
    }
}