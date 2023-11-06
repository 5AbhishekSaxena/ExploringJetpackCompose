package tech.developingdeveloper.exploringjetpackcompose.cameraapp.takepicture

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CameraScreenViewModel: ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun onPhotoTaken(bitmap: Bitmap) {
//        _bitmaps.value += bitmap
        _bitmaps.update { it + bitmap }
    }
}