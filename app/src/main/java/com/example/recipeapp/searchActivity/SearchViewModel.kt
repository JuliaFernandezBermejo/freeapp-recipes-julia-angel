package com.example.recipeapp.searchActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.common.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val searchRepository = SearchRepository()
    enum class SearchType(val label: String) {
        NAME("Name"), SERVINGS("Servings"), DIFFICULTY("Difficulty"), CUISINE("cuisine")
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
                    SearchType.NAME -> searchRepository.searchByName(query)
                    SearchType.SERVINGS -> searchRepository.searchByServings(query)
                    SearchType.DIFFICULTY -> searchRepository.searchByDifficulty(query)
                    SearchType.CUISINE -> searchRepository.searchByCuisine(query)
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