package tech.developingdeveloper.exploringjetpackcompose.cameraapp

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.qrcodescanner.QRCodeScannerMLKitApp

const val CAMERA_ROUTE = "camera"

@Composable
@ExperimentalGetImage
fun CameraApp() {
//    CameraScreen()
//    LandmarkDetectionApp()
    QRCodeScannerMLKitApp()
}