package tech.developingdeveloper.exploringjetpackcompose.cameraapp.qrcodescanner

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

@ExperimentalGetImage
class QRCodeAnalyzer(
    private val onResults: (List<Barcode>) -> Unit,
    private val onFailure: (Exception) -> Unit,
) : ImageAnalysis.Analyzer {

    private val barCodeScanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    )

    override fun analyze(imageProxy: ImageProxy) {
        val image = imageProxy.image

        if (image == null) {
            imageProxy.close()
            return
        }

        val inputImage = InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees)
        barCodeScanner.process(inputImage)
            .addOnSuccessListener(onResults)
            .addOnFailureListener(onFailure)
            .addOnCompleteListener { imageProxy.close() }
    }
}