package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelogin.ui.theme.ComposeLoginTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }
            }
        }
    }
}


@Composable
fun Login(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(all = 10.dp), verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally) {


        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var passwordVisibility by remember {
            mutableStateOf(false)
        }


        val icon = if (passwordVisibility) Icons.Default.Check else Icons.Default.Clear


        Image(imageVector = Icons.Default.AccountCircle, contentDescription = "", modifier = Modifier.size(150.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "lblEmail") },
            placeholder = {
                Text(text = "email")
            }
        )

        OutlinedTextField(
            value = password,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it },
            label = { Text(text = "lblPassword") },
            placeholder = {
                Text(text = "password")
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility}) {
                    Icon(imageVector = icon, contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        )

        Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ComposeLoginTheme {
        Login()
    }
}