package es.uji.vj1229.fortuji.imagesActivity

import androidx.lifecycle.ViewModel

class ImagesViewModel : ViewModel() {
    var view: ImagesView? = null
        set(value) {
            field = value
            view?.showImages(images)
        }
    lateinit var images: List<Pair<String, String>>
}