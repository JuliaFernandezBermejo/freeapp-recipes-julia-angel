package com.example.recipeapp.favouritesActivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipeapp.common.Recipe
import com.example.recipeapp.databinding.ActivityFavouritesBinding

class FavouritesActivity : AppCompatActivity(), FavouritesView {

    private val newsViewModel: FavouritesViewModel by viewModels()
    private lateinit var binding: ActivityFavouritesBinding
    var selectedFavourites: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
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

    /*override fun showFavourites(news: ArrayList<Recipe>) {
        TODO("Not yet implemented")
    }*/
}