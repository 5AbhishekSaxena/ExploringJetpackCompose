package tech.developingdeveloper.exploringjetpackcompose.cameraapp.ml.landmark.detection

import android.graphics.Bitmap

interface LandmarkClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}