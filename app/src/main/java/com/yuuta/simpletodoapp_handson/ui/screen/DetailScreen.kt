package com.yuuta.simpletodoapp_handson.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
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
           modifier = Modifier.padding(it)
               .padding(16.dp)
       ) {
           Row {
               Text(
                   modifier = Modifier.padding(bottom = 16.dp),
                   text = todo.text,
                   style = TextStyle(fontSize = 30.sp),
               )
               Checkbox(checked = todo.isDone, onCheckedChange = { updateTodo(todo.copy(isDone = it)) })
           }
           Spacer(modifier = Modifier.padding(16.dp))
           Row(horizontalArrangement = Arrangement.End) {
               ElevatedButton(onClick = {navigateToMain()}) {
                   Text("メイン画面に戻る")
               }
               Text("ステータス：　${if(todo.isDone) "完了" else "未完了"}")
           }
       }
    }
}

