package com.example.commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.BlankScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseApp()
        }
    }
}

@Composable
fun BaseApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavItem.Main.route) {
        composable(AppNavItem.Main.route) {
            MainScreen(navController = navController)
        }
        composable(AppNavItem.Blank.route) {
            BlankScreen()
        }
    }
}
@Composable
fun MainScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        ProductEx(
            text = text,
            textChange = {
                text = it
            },
            navController = navController,
        )
    }
}

@Composable
fun ProductEx(
    text: String,
    textChange: (String) -> Unit,
    navController: NavController,
) {
    val products = listOf(
        "asd", "asd", "asd", "asd", "asd", "qwe",
        "qwe", "zxc", "zxc", "asdasd", "dsadsa", "dsadsa",
        "asd", "asd", "asd", "asd", "asd", "qwe",
        "qwe", "zxc", "zxc", "asdasd", "dsadsa", "dsadsa",
    )
    Column {
        BasicTextField(
            value = text,
            onValueChange = { textChange(it) },
            modifier = Modifier
                .padding(top = 15.dp)
                .size(220.dp, 40.dp)
                .align(Alignment.CenterHorizontally)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
        ) {
            Row {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    fontSize = 20.sp,
                    text = text,
                    modifier = Modifier
                        .padding(horizontal = 3.dp, vertical = 2.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products.size / 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(AppNavItem.Blank.route)
                            }
                            .size(140.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize(0.8f)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = "productImage"
                        )
                        Text(
                            text = products[it],
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    Column(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(AppNavItem.Blank.route)
                            }
                            .size(140.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize(0.8f).align(Alignment.CenterHorizontally),
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = "productImage"
                        )
                        Text(
                            text = products[it],
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

sealed class AppNavItem(val route: String) {

    object Main : AppNavItem("main")

    object Blank : AppNavItem("blank")
}