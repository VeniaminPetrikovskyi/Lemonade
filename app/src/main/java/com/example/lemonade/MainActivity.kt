package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 15.dp, color = Color.Yellow),
        title = {
            Text(
                text = "Lemonade",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Yellow),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pacifico))
            )
        }
    )
}


@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var curState by remember { mutableIntStateOf(1) }
    var counter = 1
    var squeeze = 0
    val picture: Painter
    val contentDescription: String
    val text: String
    when (curState) {
        1 -> {
            picture = painterResource(id = R.drawable.lemon_tree)
            contentDescription = stringResource(id = R.string.lemon_tree_content_description)
            text = stringResource(id = R.string.select_lemon)
        }

        2 -> {
            squeeze = (2..4).random()
            picture = painterResource(id = R.drawable.lemon_squeeze)
            contentDescription = stringResource(id = R.string.lemon_content_description)
            text = stringResource(id = R.string.squeeze_lemon)
        }

        3 -> {
            picture = painterResource(id = R.drawable.lemon_drink)
            contentDescription = stringResource(id = R.string.lemonade_glass_content_description)
            text = stringResource(id = R.string.drink_lemonade)
        }

        else -> {
            picture = painterResource(id = R.drawable.lemon_restart)
            contentDescription = stringResource(id = R.string.empty_glass_content_description)
            text = stringResource(id = R.string.repeat)
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            when (curState) {
                1 -> curState = 2
                2 -> if (counter < squeeze) counter += 1 else curState = 3
                3 -> curState = 4
                else -> curState = 1
            }
        }) {
            Image(
                painter = picture,
                contentDescription = contentDescription
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    AppHeader()
    Lemonade(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}