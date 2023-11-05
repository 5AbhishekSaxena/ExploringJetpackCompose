package tech.developingdeveloper.exploringjetpackcompose.permissions

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private enum class Screen {
    NONE, CAMERA, FILES
}

@Composable
fun RuntimePermissionsScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            var currentState by remember { mutableStateOf(Screen.NONE) }

            BackHandler(enabled = false/*currentState == Screen.NONE*/) {
                Log.e("BackHandler", "BackHandler on click pressed!")
                currentState = Screen.NONE
            }

            when (currentState) {
                Screen.NONE -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Button(
                            onClick = { currentState = Screen.CAMERA }
                        ) {
                            Text("Camera Permissions")
                        }

                        Button(
                            onClick = { currentState = Screen.FILES }
                        ) {
                            Text("Files Permissions")
                        }
                    }
                }

                Screen.CAMERA -> RuntimePermissionsContent()
                Screen.FILES -> ReadFilesContent()
            }
        }
    }
}