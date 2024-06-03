package com.yuuta.simpletodoapp_handson.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.yuuta.simpletodoapp_handson.data.Todo

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Todo) -> Unit,
    todoList: List<Todo>,
    onAddTodo: (Todo) -> Unit,
    onRemove: (todo: Todo) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    var isAddTodoDialogShown by remember {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isAddTodoDialogShown = true
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Todo")
            }
        },
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                state = lazyListState,
            ) {
                items(todoList, key = { it.id }) { todo ->
                    TodoItem(
                        todo = todo,
                        onTapItem = {
                            navigateToDetail(it)
                        },
                        onRemove = onRemove
                    )
                }
            }

            if (isAddTodoDialogShown) {
                AddTodoDialog(
                    onAddTodo = {
                        onAddTodo(Todo(text = it))
                        isAddTodoDialogShown = false
                    },
                    onDismissRequest = {
                        isAddTodoDialogShown = false
                    }
                )
            }
        }
    }
}

@Composable
fun LazyItemScope.TodoItem(
    modifier: Modifier = Modifier,
    todo: Todo,
    onTapItem: (Todo) -> Unit,
    onRemove: (todo: Todo) -> Unit,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .animateItem()
            .clickable {
                onTapItem(todo)
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = todo.text,
                style = TextStyle(fontSize = 24.sp),
            )
            IconButton(
                onClick = {
                    onRemove(todo)
                }
            ) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = "Remove")
            }
        }
    }
}

@Composable
fun AddTodoDialog(
    modifier: Modifier = Modifier,
    onAddTodo: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var userInputText by remember {
        mutableStateOf("")
    }
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "新しいTodoを追加"
            )
            TextField(
                value = userInputText,
                onValueChange = { userInputText = it }
            )
            Row {
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(text = "キャンセル")
                }
                TextButton(
                    onClick = {
                        onAddTodo(userInputText)
                    }
                ) {
                    Text(text = "追加")
                }
            }
        }
    }
}
