package es.uji.vj1229.fortuji.searchActivity

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uji.vj1229.fortuji.FortniteRepository
import es.uji.vj1229.fortuji.common.Cosmetic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(){
    var view : SearchView? = null
        set(value) {
            field = value
//            onSearchRequested( "test") //SearchType.ID,
        }

    enum class SearchType(val label: String) {
        NAME("Name"),
        DESCRIPTION("Description"),
        ID("ID")
    }

    val cosmetics = ArrayList<Cosmetic>()

    var selectedCosmetic: Cosmetic? = null

    private fun showCosmetics() =
        view?.apply {
            showCosmetics(cosmetics)
        }

    fun onSearchRequested( searchType: SearchType, text: String) { //searchType: SearchType,
        viewModelScope.launch(Dispatchers.Main) {
            when (searchType){
                SearchType.NAME -> {
                    FortniteRepository.getCosmeticsByName(text).onSuccess {
                        cosmetics.clear()
                        cosmetics.addAll(it)
                        showCosmetics()
                        Log.d("Success", "Oki doki los cosmeticos")
                    }
                        .onFailure {
                            Log.d("Fallo", "Los cosmetics no cargan")
                            view?.showError("\"$text\" not found")
                        }
                }

                SearchType.DESCRIPTION -> {
                    FortniteRepository.getCosmeticsByDescription(text).onSuccess {
                        cosmetics.clear()
                        cosmetics.addAll(it)
                        showCosmetics()
                        Log.d("Success", "Oki doki los cosmeticos")
                    }
                        .onFailure {
                            Log.d("Fallo", "Los cosmetics no cargan")
                            view?.showError("Error Loading Cosmetics")
                        }
                }

                SearchType.ID -> {
                    FortniteRepository.getCosmeticsByID(text).onSuccess {
                        cosmetics.clear()
                        cosmetics.addAll(it)
                        showCosmetics()
                        Log.d("Success", "Oki doki los cosmeticos")
                    }
                        .onFailure {
                            Log.d("Fallo", "Los cosmetics no cargan")
                            view?.showError("Error Loading Cosmetics")
                        }
                }
            }
        }
    }

    fun onCosmeticSelected(cosmetic: Cosmetic) {
        selectedCosmetic = cosmetic
        view?.showCurrentCosmeticDetails()
    }

    fun onShowImagesRequested() = selectedCosmetic?.let {
        val images = ArrayList<String>()
        for (entry in it.images.entries) {
            images.add(entry.key)
            images.add(entry.value)
        }

        view?.startImagesActivity(it.name, images)
    }

    fun onStartVideoRequested() {
        view?.startVideo(selectedCosmetic?.video)
    }
}