package es.uji.vj1229.fortuji.imagesActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.uji.vj1229.fortuji.R
import es.uji.vj1229.fortuji.databinding.ActivityImagesBinding

class ImagesActivity : AppCompatActivity(), ImagesView {
    private val imagesViewModel: ImagesViewModel by viewModels()
    private lateinit var binding: ActivityImagesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imagesArray = intent.getStringArrayListExtra(IMAGES)
        val imageList = mutableListOf<Pair<String, String>>()

        if (imagesArray != null) {
            for (i in 0 ..< imagesArray.size step 2) {
                imageList.add(Pair<String, String>(imagesArray[i], imagesArray[i+1]))
            }
        }

        Log.d("Images", imageList.size.toString())
        imagesViewModel.images = imageList

        binding.imagesTextView.text = intent.getStringExtra(NAME)
    }

    override fun onResume() {
        super.onResume()
        imagesViewModel.view = this
    }

    override fun onPause() {
        super.onPause()
        imagesViewModel.view = null
    }

    companion object {
        const val NAME = "NAME"
        const val IMAGES = "IMAGES"
    }

    override fun showImages(images: List<Pair<String, String>>) {
        binding.imagesRecyclerView.run {
            adapter = ImagesAdapter(images)
            layoutManager = LinearLayoutManager(this@ImagesActivity)
        }
    }
}