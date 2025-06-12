package es.uji.vj1229.fortuji.newsActivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import es.uji.vj1229.fortuji.R
import es.uji.vj1229.fortuji.common.News
import es.uji.vj1229.fortuji.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity(), NewsView {

    private val newsViewModel : NewsViewModel by viewModels()
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        newsViewModel.view = this
    }

    override fun onPause() {
        super.onPause()
        newsViewModel.view = null
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .into(binding.newsImageView)
    }

    override fun showNews(news: ArrayList<News>) {
        binding.newsRecyclerView.run {
            adapter = NewsAdapter(news) {
                newsViewModel.onNewsSelected(it)
            }
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
    }

    override fun showCurrentNewsDetails() {
        NewsDialog()
            .show(supportFragmentManager, "NewsDialog")
    }
}