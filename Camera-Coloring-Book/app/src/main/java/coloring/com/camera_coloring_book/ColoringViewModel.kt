package coloring.com.camera_coloring_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coloring.com.camera_coloring_book.Util.SingleLiveEvent

class ColoringViewModel : ViewModel() {

    val _selectColorEvent = SingleLiveEvent<Any>()
    val _paintColorString = MutableLiveData<String>()
    val _loadImage = SingleLiveEvent<Any>()

    fun selectColorEvent() {
        _selectColorEvent.call()
    }

    fun loadImage() {
        _loadImage.call()
    }




}