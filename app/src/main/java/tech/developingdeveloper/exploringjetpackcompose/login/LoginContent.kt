package tech.developingdeveloper.exploringjetpackcompose.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.developingdeveloper.exploringjetpackcompose.R
import tech.developingdeveloper.exploringjetpackcompose.login.theme.Black
import tech.developingdeveloper.exploringjetpackcompose.login.theme.BlueGray
import tech.developingdeveloper.exploringjetpackcompose.login.theme.Roboto

@Composable
fun LoginContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onFacebookLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        val uiColor = if (isSystemInDarkTheme()) Color.White else Black

        BackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.12f))

            AppLogoAndName(
                uiColor = uiColor,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.headlineLarge,
                color = uiColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(36.dp))

            LoginForm(
                email = email,
                onEmailChange = onEmailChange,
                password = password,
                onPasswordChange = onPasswordChange,
                onLoginButtonClick = onLoginButtonClick,
                onGoogleLoginClick = onGoogleLoginClick,
                onFacebookLoginClick = onFacebookLoginClick,
            )

            CreateAccount(
                onCreateAccountClick = onCreateAccountClick,
                uiColor = uiColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.login_bg),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.46f),
        contentScale = ContentScale.FillBounds,
        colorFilter = ColorFilter.tint(
            if (isSystemInDarkTheme()) Color(0xFF1E293B) else Color(0xFFBFDBFE)
        ),
    )
}

@Composable
private fun AppLogoAndName(uiColor: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.login_screen_logo),
            contentDescription = "logo",
            tint = uiColor,
            modifier = Modifier.size(42.dp),
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(
                text = stringResource(id = R.string.the_tolet),
                style = MaterialTheme.typography.headlineMedium,
                color = uiColor,
            )
            Text(
                text = stringResource(id = R.string.find_house),
                style = MaterialTheme.typography.titleMedium,
                color = uiColor,
            )
        }
    }
}

@Composable
private fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onFacebookLoginClick: () -> Unit
) {
    Column(modifier = Modifier.padding(32.dp)) {
        EmailInput(email, onEmailChange)

        Spacer(modifier = Modifier.height(16.dp))

        PasswordInput(password, onPasswordChange)

        Spacer(modifier = Modifier.height(20.dp))

        LoginButton(onLoginButtonClick)

        Spacer(Modifier.height(32.dp))

        SocialMediaOAuthDivider()

        Spacer(modifier = Modifier.height(32.dp))

        SocialMediaOAuth(onGoogleLoginClick, onFacebookLoginClick)
    }
}

@Composable
private fun EmailInput(email: String, onEmailChange: (String) -> Unit) {
    LoginTextField(
        value = email,
        onValueChange = onEmailChange,
        label = "Email",
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
    )
}

@Composable
private fun PasswordInput(password: String, onPasswordChange: (String) -> Unit) {
    PasswordLoginTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = "Password",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun LoginButton(onLoginButtonClick: () -> Unit) {
    Button(
        onClick = onLoginButtonClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) BlueGray else Black,
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium
            ),
        )
    }
}

@Composable
private fun SocialMediaOAuthDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Divider(Modifier.weight(1f))
        Text(
            text = "OR",
            style = MaterialTheme.typography.labelMedium.copy(Color(0xFF647488)),
            modifier = Modifier.padding(horizontal = 32.dp),
        )
        Divider(Modifier.weight(1f))
    }
}

@Composable
private fun SocialMediaOAuth(
    onGoogleLoginClick: () -> Unit,
    onFacebookLoginClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        SocialMediaButton(
            text = "Google",
            iconRes = R.drawable.google_logo,
            onClick = onGoogleLoginClick,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(20.dp))

        SocialMediaButton(
            text = "Facebook",
            iconRes = R.drawable.facebook_logo,
            onClick = onFacebookLoginClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun CreateAccount(
    onCreateAccountClick: () -> Unit,
    uiColor: Color,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onCreateAccountClick,
        modifier = modifier,
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF94A388),
                        fontSize = 14.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal,
                    )
                ) {
                    append("Don't have an account?")
                }

                append(" ")

                withStyle(
                    style = SpanStyle(
                        color = uiColor,
                        fontSize = 14.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                    )
                ) {
                    append("Create Now")
                }
            },
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun LoginContentPreview() {
    LoginScreenTheme {
        Surface {
            LoginContent(
                email = "",
                onEmailChange = {},
                password = "",
                onPasswordChange = {},
                onLoginButtonClick = {},
                onGoogleLoginClick = {},
                onFacebookLoginClick = {},
                onCreateAccountClick = {})
        }
    }
}