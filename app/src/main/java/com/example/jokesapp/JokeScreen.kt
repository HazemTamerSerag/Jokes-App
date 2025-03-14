package com.example.jokesapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JokeScreen(modifier: Modifier = Modifier) {
    val jokes = listOf(R.string.joke_1, R.string.joke_2, R.string.joke_3, R.string.joke_4)
    var randomJoke by remember { mutableIntStateOf(R.string.need_jokes) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Card(
            border = BorderStroke(2.dp,
                Brush.horizontalGradient(
                    listOf(Color.Red, Color.Green)
                )
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.8f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(randomJoke),
                    fontSize = 24.sp,
                    style = TextStyle(
                        brush = Brush.horizontalGradient(
                            listOf(Color.Red, Color.Green)
                        )
                    ),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
                TextButton(
                    onClick = {
                        randomJoke = jokes.random()
                    },
                    modifier = modifier
                        .padding(top = 16.dp)
                        .border(
                            BorderStroke(
                                2.dp,
                                Brush.horizontalGradient(
                                    listOf(Color.Red, Color.Green)
                                )
                            ),
                            shape = RoundedCornerShape(24.dp)
                        )
                ) {
                    Text(text = stringResource(R.string.haha_me))
                }
            }
        }
    }
}
@Preview (showBackground = true,showSystemUi = true)
@Composable
private fun JokeScreenPreview() {
    JokeScreen()
}