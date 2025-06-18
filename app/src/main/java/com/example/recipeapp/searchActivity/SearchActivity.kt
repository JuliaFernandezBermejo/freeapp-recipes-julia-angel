package com.example.recipeapp.searchActivity

import com.example.recipeapp.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.common.Recipe
import com.example.recipeapp.databinding.ActivitySearchBinding
import com.example.recipeapp.favouritesActivity.FavouritesViewModel
import com.example.recipeapp.network.RecipeResponse
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity(), SearchView {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: ActivitySearchBinding
    private lateinit var categorySpinner: Spinner
    private lateinit var adapter: RecipeAdapter
    private lateinit var searchInput: EditText
    private lateinit var searchButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorTextView: TextView


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

        searchViewModel = SearchViewModel()
        searchViewModel.view = this
        errorTextView = binding.errorTextView
        searchInput = binding.SearchContenttextView
        searchButton = binding.Searchbutton
        categorySpinner = binding.searchTypeSpinner
        recyclerView = binding.SearchRecyclerView

        adapter = RecipeAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        categorySpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            SearchViewModel.SearchType.values()
        )

        binding.Searchbutton.setOnClickListener {
            searchViewModel.onSearchRequested(
                binding.searchTypeSpinner.selectedItem as SearchViewModel.SearchType,
                binding.SearchContenttextView.text.toString()
            )
        }
        }

    override fun showRecipes(recipes: List<RecipeResponse>) {
            adapter.updateData(recipes)

    }

    override fun showError(s: String) {
            errorTextView.text = s
    }
    override fun onResume() {
        super.onResume()
        searchViewModel.view= this
    }

    override fun onPause() {
        super.onPause()
        searchViewModel.view = null
    }
}