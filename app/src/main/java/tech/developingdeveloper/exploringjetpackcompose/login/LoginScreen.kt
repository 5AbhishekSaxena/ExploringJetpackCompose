package tech.developingdeveloper.exploringjetpackcompose.login

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

const val LOGIN_SCREEN_ROUTE = "login_screen"

@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginScreenTheme {
        Surface {
            LoginContent(
                email = email,
                onEmailChange = {
                    email = it
                },
                password = password,
                onPasswordChange = {
                    password = it
                },
                onLoginButtonClick = {},
                onGoogleLoginClick = {},
                onFacebookLoginClick = {},
                onCreateAccountClick = {}
            )
        }
    }
}