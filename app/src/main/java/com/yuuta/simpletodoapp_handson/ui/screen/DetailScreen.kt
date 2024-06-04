package com.yuuta.simpletodoapp_handson.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuuta.simpletodoapp_handson.data.Todo

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit,
    todo: Todo,
    updateTodo: (Todo) -> Unit,
) {
    Scaffold(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .weight(1f),
                    text = todo.text,
                    style = TextStyle(fontSize = 30.sp),
                )
                Checkbox(
                    checked = todo.isDone,
                    onCheckedChange = { updateTodo(todo.copy(isDone = it)) })
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Row(horizontalArrangement = Arrangement.End) {
                ElevatedButton(onClick = { navigateToMain() }) {
                    Text("メイン画面に戻る")
                }
                Text("ステータス：　${if (todo.isDone) "完了" else "未完了"}")
            }
        }
    }
}

