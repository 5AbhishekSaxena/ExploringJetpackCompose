package tech.developingdeveloper.exploringjetpackcompose.cameraapp.qrcodescanner

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.common.Barcode
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.takepicture.CameraPreview

@Composable
@ExperimentalGetImage
fun QRCodeScannerMLKitScreen() {
    val context = LocalContext.current

    var barcodes by remember {
        mutableStateOf(emptyList<String>())
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var processImages by remember { mutableStateOf(true) }
    var isCameraEnabled by remember { mutableStateOf(true) }

    val analyzer = remember {
        QRCodeAnalyzer(
            onResults = {
                if (errorMessage.isNotBlank())
                    errorMessage = ""

                barcodes = it
                    .mapNotNull(Barcode::getDisplayValue)
                    .filter(String::isNotBlank)
            },
            onFailure = { errorMessage = it.message ?: "Something went wrong." }
        )
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                analyzer
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .background(Color.Red.copy(alpha = 0.4f))
                    .padding(8.dp)
            ) {
                Text("Camera: $isCameraEnabled")
                Text("Analysis: $processImages")
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(
                    onClick = {
                        isCameraEnabled = true
                        controller.bindToLifecycle(lifecycleOwner)
                    }
                ) {
                    Text("Start Camera")
                }
                Button(
                    onClick = {
                        isCameraEnabled = false
                        controller.unbind()
                    }
                ) {
                    Text("Stop Camera")
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(
                    onClick = {
                        if (!processImages) {
                            controller.setImageAnalysisAnalyzer(
                                ContextCompat.getMainExecutor(
                                    context
                                ), analyzer
                            )
                            processImages = true
                        }
                        barcodes = emptyList()
                    }
                ) {
                    Text("Start Image Analysis")
                }
                Button(
                    onClick = {
                        if (processImages) {
                            controller.clearImageAnalysisAnalyzer()
                            processImages = false
                        }
                        barcodes = emptyList()
                    }
                ) {
                    Text("Stop Image Analysis")
                }
            }
        }

        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }

    barcodes.forEach {
        Text(
            text = it,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
