package com.example.recipeapp.searchActivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recipeapp.common.Recipe
import com.example.recipeapp.databinding.ActivityFavouritesBinding
import com.example.recipeapp.favouritesActivity.FavouritesViewModel

class SearchActivity : AppCompatActivity(), SearchView {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
}