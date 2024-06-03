package com.yuuta.simpletodoapp_handson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yuuta.simpletodoapp_handson.data.Todo
import com.yuuta.simpletodoapp_handson.ui.screen.DetailScreen
import com.yuuta.simpletodoapp_handson.ui.screen.MainScreen
import com.yuuta.simpletodoapp_handson.ui.screen.Screens
import com.yuuta.simpletodoapp_handson.ui.theme.SimpleTodoApp_HandsOnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val todoList = remember {
                mutableStateListOf(
                    Todo(text = "きゅうりを買う"),
                    Todo(text = "キャベツを買う"),
                    Todo(text = "卵を買う"),
                )
            }
            SimpleTodoApp_HandsOnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.MainScreen.name
                    ) {
                        composable(Screens.MainScreen.name) {
                            MainScreen(
                                navigateToDetail = {
                                    navController.navigate("${Screens.DetailScreen.name}/${it.id}")
                                },
                                todoList = todoList,
                                onAddTodo = {
                                    todoList.add(it)
                                },
                                onRemove = {
                                    todoList.remove(it)
                                }
                            )
                        }
                        composable("${Screens.DetailScreen.name}/{todoId}", arguments = listOf(
                            navArgument("todoId") {
                                type = NavType.StringType
                            }
                        )) {
                            val todoId = it.arguments?.getString("todoId")
                            DetailScreen(
                                navigateToMain = {
                                    navController.popBackStack()
                                },
                                todo = todoList.first { it.id == todoId },
                                updateTodo = {
                                    val index = todoList.indexOfFirst { it.id == todoId }
                                    todoList[index] = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleTodoApp_HandsOnTheme {
        Greeting("Android")
    }
}
