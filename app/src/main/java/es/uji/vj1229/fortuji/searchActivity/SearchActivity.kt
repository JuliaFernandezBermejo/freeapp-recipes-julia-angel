package es.uji.vj1229.fortuji.searchActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import es.uji.vj1229.fortuji.R
import es.uji.vj1229.fortuji.common.Cosmetic
import es.uji.vj1229.fortuji.common.News
import es.uji.vj1229.fortuji.databinding.ActivitySearchBinding
import es.uji.vj1229.fortuji.imagesActivity.ImagesActivity

class SearchActivity : AppCompatActivity(), SearchView{
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val options = SearchViewModel.SearchType.entries
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.searchTypeSpinner.adapter = spinnerAdapter

        binding.searchTypeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        binding.errorTextView.visibility = RecyclerView.GONE
        binding.searchButton.setOnClickListener {
            searchViewModel.onSearchRequested(
                binding.searchTypeSpinner.selectedItem as SearchViewModel.SearchType,
                binding.searchContentEditText.text.toString()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.view = this
    }

    override fun onPause() {
        super.onPause()
        searchViewModel.view = null
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        binding.errorTextView.visibility = View.VISIBLE
        binding.cosmeticsRecyclerView.visibility = View.GONE
        binding.errorTextView.text = message
    }

    override fun showCosmetics(cosmetics: List<Cosmetic>) {
        binding.errorTextView.visibility = View.GONE
        binding.cosmeticsRecyclerView.visibility = View.VISIBLE
        binding.cosmeticsRecyclerView.run {
            adapter = SearchAdapter(cosmetics) {
                searchViewModel.onCosmeticSelected(it)
            }
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    override fun showCurrentCosmeticDetails() {
        Log.d("Success", "Showing current cosmetic")
        SearchDialog()
            .show(supportFragmentManager, "SearchDialog")
    }

    override fun startImagesActivity(name: String, images: ArrayList<String>) {
        val intent = Intent(this, ImagesActivity::class.java)
        intent.putExtra(ImagesActivity.NAME, name)
        intent.putExtra(ImagesActivity.IMAGES, images)
        startActivity(intent)
    }

    override fun startVideo(video: String?) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("vnd.youtube:$video")
        }
        startActivity(intent)
    }
}