package tech.developingdeveloper.exploringjetpackcompose.cameraapp.ml.landmark.detection.data

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.Surface
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.ml.landmark.detection.Classification
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.ml.landmark.detection.LandmarkClassifier

class TFLiteLandmarkClassifier(
    private val context: Context,
    private val threshold: Float = 0.5f,
    private val maxResults: Int = 2,
) : LandmarkClassifier {

    private var classifier: ImageClassifier? = null

    override fun classify(bitmap: Bitmap, rotation: Int): List<Classification> {
        if (classifier == null)
            setupClassifier()

        val imageProcessor = ImageProcessor.Builder().build()
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))

        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setOrientation(getOrientationFromRotation(rotation))
            .build()

        val results = classifier?.classify(tensorImage, imageProcessingOptions)
        val result = results?.flatMap { classifications ->
            classifications.categories.map { category ->
                Classification(
                    name = category.displayName,
                    score = category.score
                )
            }
        }?.distinctBy { it.name } ?: emptyList()

        Log.e(javaClass.name, "classify, result1: $result")

        return result
    }

    private fun setupClassifier() {
        val baseOptions = BaseOptions.builder()
            .setNumThreads(2)
            .build()
        val options = ImageClassifier.ImageClassifierOptions.builder()
            .setBaseOptions(baseOptions)
            .setMaxResults(maxResults)
            .setScoreThreshold(threshold)
            .build()

        try {
            classifier = ImageClassifier.createFromFileAndOptions(
                context,
                "landmark_classifier.tflite",
                options,
            )
        } catch (exception: IllegalStateException) {
            exception.printStackTrace()
        }
    }

    private fun getOrientationFromRotation(rotation: Int): ImageProcessingOptions.Orientation {
        return when (rotation) {
            Surface.ROTATION_90 -> ImageProcessingOptions.Orientation.TOP_LEFT
            Surface.ROTATION_180 -> ImageProcessingOptions.Orientation.RIGHT_BOTTOM
            Surface.ROTATION_270 -> ImageProcessingOptions.Orientation.BOTTOM_RIGHT
            else -> ImageProcessingOptions.Orientation.RIGHT_TOP
        }
    }
}