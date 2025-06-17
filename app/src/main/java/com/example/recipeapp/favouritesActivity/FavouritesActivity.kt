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
import com.example.recipeapp.network.RecipeResponse

class FavouritesActivity : AppCompatActivity(), FavouritesView {


    private lateinit var binding: ActivityFavouritesBinding
    private val favouritesViewModel: FavouritesViewModel by viewModels()

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
        favouritesViewModel.view = this
    }

    override fun onPause() {
        super.onPause()
        favouritesViewModel.view = null
    }

    override fun showFavourites(favourites: List<RecipeResponse>) {
        binding.FavouritesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavouritesActivity)
            adapter = FavouritesAdapter(favourites)
            binding.FavouritesRecyclerView.layoutManager = LinearLayoutManager(this@FavouritesActivity)
            binding.FavouritesRecyclerView.adapter = adapter

        }
    }

    override fun showError(string: String) {

    }
}