package com.example.composelogin.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.composelogin.Constants
import com.example.composelogin.R
import com.example.composelogin.ui.main.MainActivity
import com.example.composelogin.ui.theme.ComposeLoginTheme
import kotlinx.coroutines.delay

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            ComposeLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen {name, surname, email ->
                        goMainActivity(context, name, surname, email)
                    }
                }
            }
        }
    }
}

fun goMainActivity(context: Context, name: String, surname: String, email: String) {
    val intent = Intent(context, MainActivity::class.java).apply {
        putExtra(ARG_NAME, name)
        putExtra(ARG_SURNAME, surname)
        putExtra(ARG_EMAIL, email)
    }
    context.startActivity(intent)
}

const val ARG_NAME = "arg_name"
const val ARG_SURNAME = "arg_surname"
const val ARG_EMAIL = "arg_email"

@Composable
fun LoginScreen(navigateToMain:(name:String, surname:String, email:String)->Unit) {
    Box(modifier = Modifier.fillMaxSize()){
        val navController = rememberNavController()
        var visibleSocialMediaArea by remember {
            mutableStateOf(true)
        }
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                Login(navigateRegister = { navController.navigate("register") }, navigateToMain = {email->
                    navigateToMain("", "", email)
                })
            }
            composable("register") {
                Register(
                    onSnackbarVisibleListener = { snackbarVisiblity ->
                        visibleSocialMediaArea = !snackbarVisiblity
                    },
                    navigateLogin = { navController.navigate("login") },
                    navigateToMain = {name, surname, email ->
                        navigateToMain(name, surname, email)
                    }
                )
            }
        }
        if (visibleSocialMediaArea) SocialMediaArea(modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Preview
@Composable
private fun LoginMainScreenPreview() {
    MaterialTheme{
        LoginScreen(
            navigateToMain = {name, surname, email ->  }
        )
    }
}

@Composable
fun ImageSlider() {
    var randomImage by remember { mutableStateOf(Constants.flowerLinks.random()) }

    Box{
        AsyncImage(
            model = randomImage,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.slider_image_white_shadow)))

        SliderProgressBar {
            randomImage = Constants.flowerLinks.random()
        }
    }
}

@Preview
@Composable
fun ImageSliderPreview(){
    ImageSlider()
}

@Composable
fun SliderProgressBar(timeFinished:()->Unit){

    var progress by remember { mutableStateOf(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5)
            progress -= 0.001f
            if (progress <= 0f) {
                timeFinished()
                progress = 0f
                progress = 1.0f
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(
                color = Color.Transparent, shape = RoundedCornerShape(4.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp)
                )
        )
    }
}


@Composable
fun ProfileImageComponent(randomUserImageLink: String) {
    var borderSize by remember { mutableStateOf(1.dp) }

    val animatedBorderSize by animateFloatAsState(
        targetValue = borderSize.value,
        animationSpec = tween(durationMillis = 1000), label = ""
    )
    var borderColor by remember { mutableStateOf(Color.Black) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            borderSize = (1..10).random().dp
            borderColor = Color(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
        }
    }

    AsyncImage(
        placeholder = painterResource(id = R.drawable.ic_spa_duo),
        model = randomUserImageLink,
        contentDescription = "",
        modifier = Modifier
            .size(150.dp)
            .fillMaxSize()
            .clip(CircleShape)
            .border(animatedBorderSize.dp, color = borderColor, shape = CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MyAlertDialog(
    icon: Painter,
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    onConfirmation:() -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(id = R.string.login_screen_logged_in_dialog_confirm_button_text))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.login_screen_logged_in_dialog_dismiss_button_text))
            }
        }
    )
}

@Composable
fun SocialMediaArea(modifier: Modifier) {
    AnimatedVisibility(
        modifier = modifier,
        visible = true,
        enter = slideInVertically(
            // Enters by sliding down from offset -fullHeight to 0.
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            // Exits by sliding up from offset 0 to -fullHeight.
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MediaIcon(
                painter = painterResource(id = R.drawable.twitter_svgrepo_com),
                contentDescription = "twitter",
                url = "https://www.twitter.com"
            )
            MediaIcon(
                painter = painterResource(id = R.drawable.facebook_svgrepo_com),
                contentDescription = "facebook",
                url = "https://www.facebook.com"
            )
            MediaIcon(
                painter = painterResource(id = R.drawable.pinterest_svgrepo_com),
                contentDescription = "pinterest",
                url = "https://www.pinterest.com"
            )
            MediaIcon(
                painter = painterResource(id = R.drawable.vimeo_svgrepo_com),
                contentDescription = "vimeo",
                url = "https://www.vimeo.com"
            )
        }
    }
}

@Composable
fun MediaIcon(painter: Painter, contentDescription: String, url: String? = null) {
    val uriHandler = LocalUriHandler.current
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(40.dp)
            .padding(all = 10.dp)
            .clickable {
                uriHandler.openUri(url.orEmpty())
            }
    )
}