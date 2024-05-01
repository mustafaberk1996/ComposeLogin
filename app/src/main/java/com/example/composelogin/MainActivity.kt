package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.ComposeLoginTheme

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
fun Login() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        Alignment.CenterHorizontally
    ) {


        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var passwordVisibility by remember {
            mutableStateOf(false)
        }


        val icon = if (passwordVisibility) painterResource(id = R.drawable.eye_slash_path) else painterResource(
            id = R.drawable.eye_path
        )


        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            placeholder = {
                Text(text = "email")
            }
        )

        OutlinedTextField(
            value = password,
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            placeholder = {
                Text(text = "password")
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = icon, contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        )

        Button(
            onClick = { }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = "Login")
        }

        Text(text = "Or", fontSize = 12.sp, modifier = Modifier.padding(top = 10.dp))
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Register", modifier = Modifier
                    .padding(all = 5.dp)
            )
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