package com.example.composelogin.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun MainScreen(name: String, surname: String, email: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Name: $name", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Surname: $surname", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email: $email", style = MaterialTheme.typography.bodyLarge)
    }
}
