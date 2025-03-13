package com.example.jokesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Joke Data Model
data class Joke(val joke: String)

// Retrofit API Service
interface JokeApiService {
    @GET("joke/Any")
    suspend fun getJoke(): JokeResponse
}

data class JokeResponse(val joke: String)

// Retrofit Instance
val retrofit = Retrofit.Builder()
    .baseUrl("https://v2.jokeapi.dev/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val jokeApiService = retrofit.create(JokeApiService::class.java)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeApp()
        }
    }
}

@Composable
fun JokeApp() {
    var joke by remember { mutableStateOf("Tap the button for a joke!") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = joke, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                joke = try {
                    jokeApiService.getJoke().joke
                } catch (e: Exception) {
                    "Failed to fetch joke!"
                }
            }
        }) {
            Text("Tell me a joke!")
        }
    }
}
