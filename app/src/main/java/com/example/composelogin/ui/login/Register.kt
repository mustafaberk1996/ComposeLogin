package com.example.composelogin.ui.login

import android.content.Intent
import android.util.Patterns
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.R
import com.example.composelogin.ui.main.MainActivity
import kotlinx.coroutines.launch
import java.util.Locale


data class SnackbarState(
    val visible: Boolean = false,
    val message: Int = -1,
    val actionText: Int? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short
)
@Composable
fun Register(navigateLogin: () -> Unit){

    val context = LocalContext.current

    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordAgain by rememberSaveable { mutableStateOf("") }
    var snackbarState by remember { mutableStateOf(SnackbarState()) }
    var visibleSnackbar by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {contentPadding->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {

            Column(modifier = Modifier
                .padding(all = 10.dp)
                .align(alignment = Alignment.Center),
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
                    label = { Text(text = stringResource(id = R.string.register_screen_outline_textfield_email_label)) },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.register_screen_outline_textfield_email_label).lowercase(
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
                    value = passwordAgain,
                    onValueChange = { passwordAgain = it },
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
                    if (name.isBlank() || surname.isBlank() || password.isBlank() || passwordAgain.isBlank()) {
                        visibleSnackbar = true
                            snackbarState = SnackbarState(
                                message = R.string.register_screen_empty_field_snackbar_message,
                                actionText = R.string.register_screen_successfully_register_snackbar_action_button_text,
                            )
                    } else{
                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                            visibleSnackbar = true
                            if (password != passwordAgain){
                                snackbarState = SnackbarState(
                                    message = R.string.register_screen_password_match_error_snackbar_message,
                                    actionText =null,
                                )
                            }else{
                                context.startActivity(
                                    Intent(context, MainActivity::class.java)
                                )
                            }
                        }else{
                            visibleSnackbar = true
                            snackbarState = SnackbarState(
                                message = R.string.error_message_invalid_email,
                                actionText = null,
                            )
                        }
                    }

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

        if (visibleSnackbar) {
            val message = stringResource(id = snackbarState.message)
            val actionText =
                if (snackbarState.actionText != null) stringResource(id = snackbarState.actionText!!) else null

            LaunchedEffect(Unit) {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message,
                        actionText,
                        false,
                        snackbarState.duration
                    )

                    visibleSnackbar = when (result) {
                        SnackbarResult.ActionPerformed -> false
                        SnackbarResult.Dismissed -> false
                    }
                }
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
