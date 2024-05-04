package com.example.composelogin.ui.login

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.R
import java.util.Locale

@Composable
fun Register(navigateLogin: () -> Unit){

    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordAgain by rememberSaveable { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier
            .align(alignment = Alignment.Center)
            .padding(all = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(id = R.string.register_screen_outline_textfield_name_label)) },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.register_screen_outline_textfield_name_label).lowercase(
                            Locale.getDefault()
                        )
                    )
                },
            )


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                value = surname,
                onValueChange = { surname = it },
                label = { Text(text = stringResource(id = R.string.register_screen_outline_textfield_surname_label)) },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.register_screen_outline_textfield_surname_label).lowercase(
                            Locale.getDefault()
                        )
                    )
                },
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(id = R.string.register_screen_outline_textfield_password_label)) },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.register_screen_outline_textfield_password_label).lowercase(
                            Locale.getDefault()
                        )
                    )
                },
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.register_screen_outline_textfield_password_again_label)) },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.register_screen_outline_textfield_password_again_label).lowercase(
                            Locale.getDefault()
                        )
                    )
                },
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

           Button(onClick = {

           }, modifier = Modifier.fillMaxWidth()) {
               Text(text = stringResource(id = R.string.register_screen_register_button_text))
           }

            Text(text = stringResource(id = R.string.or_label_text), fontSize = 12.sp, modifier = Modifier.padding(top = 10.dp))

            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(
                        width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        navigateLogin()
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = stringResource(id = R.string.register_screen_go_login_text), modifier = Modifier
                        .padding(all = 5.dp)
                )
            }

        }
    }

}


@Composable
@Preview
fun RegisterPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Register {

            }
        }
    }
}
