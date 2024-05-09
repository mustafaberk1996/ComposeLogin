package com.example.composelogin.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelogin.ui.login.ARG_EMAIL
import com.example.composelogin.ui.login.ARG_NAME
import com.example.composelogin.ui.login.ARG_SURNAME
import com.example.composelogin.ui.theme.ComposeLoginTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra(ARG_NAME) ?: "N/A"
        val surname = intent.getStringExtra(ARG_SURNAME) ?: "N/A"
        val email = intent.getStringExtra(ARG_EMAIL) ?: "N/A"
        setContent {
            ComposeLoginTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen(name, surname, email)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeLoginTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Box {
                MainScreen("John", "Doe", "john.doe@example.com", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun MainScreen(name: String, surname: String, email: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$name $surname", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = email, style = MaterialTheme.typography.bodyLarge)
    }
}

