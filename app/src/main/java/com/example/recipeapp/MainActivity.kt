package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.favouritesActivity.FavouritesActivity
import com.example.recipeapp.searchActivity.SearchActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.favouritesButtton.setOnClickListener {
            val intent = Intent(this, FavouritesActivity::class.java)
            startActivity(intent)
        }

        binding.searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }
}
