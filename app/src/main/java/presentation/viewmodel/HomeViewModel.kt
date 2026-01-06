package presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import domain.model.ShoppingList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class HomeViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _shoppingLists = MutableStateFlow<List<ShoppingList>>(emptyList())
    val shoppingLists = _shoppingLists.asStateFlow()
    init {
        loadShoppingLists()
    }

    private fun loadShoppingLists() {
        viewModelScope.launch {
            try {
                val jsonString = application.assets.open("shopping_lists_mock.json")
                    .bufferedReader()
                    .use {it.readText()}
                val shoppingLists = Json.decodeFromString<List<ShoppingList>>(jsonString)
            } catch (e: Exception) {
                // TODO: handle error
                e.printStackTrace()
            }
        }
    }
}