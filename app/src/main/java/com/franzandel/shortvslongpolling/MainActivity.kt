package com.franzandel.shortvslongpolling

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.franzandel.shortvslongpolling.ui.theme.ShortVsLongPollingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShortVsLongPollingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
        ) {
            Button(
                onClick = ::onShortPollingClicked
            ) {
                Text(text = "Short Polling")
            }
            Button(
                onClick = ::onLongPollingClicked
            ) {
                Text(text = "Long Polling")
            }
        }
    }
}

private fun onShortPollingClicked() {
    runBlocking {
        for (i in 1..5) {
            shortPollingHTTPRequest()
            Log.d("1234 ShortPolling", "GET /payment/status API Response")
            Log.d("1234 ShortPolling", "wait for 3s")
            delay(3_000)
            Log.d("1234 ShortPolling", "3s ended")
        }
    }
}

private fun onLongPollingClicked() {
    runBlocking {
        for (i in 1..5) {
            longPollingHTTPRequest()
            for (j in 1..3) {
                delay(1_000)
                Log.d("1234 LongPolling", "Waiting ${j}s")
                if (j == 3) {
                    Log.d("1234 LongPolling", "GET /chat/message API Response")
                }
            }
        }
    }
}

private fun shortPollingHTTPRequest() {
    Log.d("1234 ShortPolling", "hit GET /payment/status API")
}

private fun longPollingHTTPRequest() {
    Log.d("1234 LongPolling", "hit GET /chat/message API")
}

@Preview(showBackground = true)
@Composable
fun ShortVsLongPollingPreview() {
    ShortVsLongPollingTheme {
        Screen()
    }
}
