package com.example.composelogin.ui.login

import android.media.MediaPlayer
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.Constants
import com.example.composelogin.R
import com.example.composelogin.ui.theme.ComposeLoginTheme
import java.util.Locale

@Composable
fun Login(navigateRegister:()->Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        ImageSlider()

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(all = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            val context = LocalContext.current

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            var emailError by remember { mutableStateOf(false) }
            var passwordError by remember { mutableStateOf(false) }

            var emailErrorMessageResource by remember { mutableIntStateOf(-1) }
            var passwordErrorMessageResource by remember { mutableIntStateOf(-1) }

            var showLoggedInDialog by remember { mutableStateOf(false) }

            var passwordVisibility by remember {
                mutableStateOf(false)
            }


            val toastMessage = stringResource(id = R.string.login_screen_logged_in_dialog_confirm_button_text)
            if (showLoggedInDialog) {
                MyAlertDialog(
                    icon = painterResource(id = R.drawable.info_circle_duo),
                    title = stringResource(id = R.string.login_screen_logged_in_dialog_title),
                    message = stringResource(id = R.string.login_screen_logged_in_dialog_message),
                    onDismissRequest = {
                        showLoggedInDialog = false
                    },
                    onConfirmation = {
                        showLoggedInDialog = false
                        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
                    }
                )
            }


            val icon =
                if (passwordVisibility) painterResource(id = R.drawable.eye_slash_path) else painterResource(
                    id = R.drawable.eye_path
                )


            val randomUserImageLink by remember {
                mutableStateOf(Constants.users.random())
            }
            ProfileImageComponent(randomUserImageLink)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(id = R.string.login_screen_outline_textfield_email_label)) },
                placeholder = {
                    Text(text = stringResource(id = R.string.login_screen_outline_textfield_email_label).lowercase(
                        Locale.getDefault()
                    ))
                },
                isError = emailError,
                supportingText = {
                    if (emailError) {
                        Text(text = stringResource(id = emailErrorMessageResource))
                    }
                }
            )

            OutlinedTextField(
                value = password,
                shape = RoundedCornerShape(26.dp),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.login_screen_outline_textfield_password_label)) },
                placeholder = {
                    Text(text = stringResource(id = R.string.login_screen_outline_textfield_password_label).lowercase(
                        Locale.getDefault()))
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(painter = icon, contentDescription = "")
                    }
                },
                isError = passwordError,
                supportingText = {
                    if (passwordError) {
                        Text(text = stringResource(id = passwordErrorMessageResource))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            )

            Button(
                onClick = {
                    var isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    emailError = email.isBlank() || !isEmailValid
                    passwordError = password.isBlank()
                    if (email.isBlank()) emailErrorMessageResource =
                        R.string.error_message_empty_email
                    else {
                        isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        if (!isEmailValid) emailErrorMessageResource =
                            R.string.error_message_invalid_email
                    }

                    if (password.isBlank()) passwordErrorMessageResource =
                        R.string.error_message_empty_password

                    if (email.isBlank() || password.isBlank() || !isEmailValid) return@Button

                    showLoggedInDialog = true
                    MediaPlayer.create(context, R.raw.collect_points).start()

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(text = stringResource(id = R.string.login_screen_login_button_text))
            }

            Text(text = stringResource(id = R.string.or_label_text), fontSize = 12.sp, modifier = Modifier.padding(top = 10.dp))
            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(
                        width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        navigateRegister()
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = stringResource(id = R.string.login_screen_go_register_text), modifier = Modifier
                        .padding(all = 5.dp)
                )
            }

        }
        SocialMediaArea(modifier = Modifier.align(Alignment.BottomCenter))
    }

}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ComposeLoginTheme {
        Login(){}
    }
}