package com.example.recipeapp.searchActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.common.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val searchRepository = SearchRepository()
    enum class SearchType(val label: String) {
        TAG("tag"), MEAL("meal-type")
    }
    var view: SearchView? = null
        set(value){
            field = value
            updateView()
        }

    fun onSearchRequested( type: SearchType, query: String) {
        viewModelScope.launch {
            try {
                val results = when (type) {
                    SearchType.TAG -> searchRepository.searchByTag(query)
                    SearchType.MEAL -> searchRepository.searchByMeal(query)
                }
                view?.showRecipes(results)
            } catch (e: Exception) {
                view?.showError("Error al buscar: ${e.message}")
            }
        }
    }
    private fun updateView() {

    }

}