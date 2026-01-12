package domain.viewmodel

import androidx.activity.result.launch
import androidx.compose.animation.core.copy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.local.ShoppingListDao
import data.local.model.CategoryList
import domain.event.HomeState
import domain.event.HomeEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    dao: ShoppingListDao
) : ViewModel() {
    private val _categories = dao.getCategoryLists()
        .stateIn(viewModelScope,SharingStarted.WhileSubscribed(), emptyList())

    private val _shoppingLists = dao.getShoppingListsByCategory(1) // TODO() dependency here
        .stateIn(viewModelScope,SharingStarted.WhileSubscribed(), emptyList())

    private val _shoppingItems = dao.getItemsByList(1) // TODO() dependency here
        .stateIn(viewModelScope,SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(HomeState())

    val state = combine(_state, _categories, _shoppingLists, _shoppingItems){ state, categories, shoppingLists, shoppingItems ->
        state.copy(
            categories = categories,
            shoppingLists = shoppingLists,
            shoppingItems = shoppingItems
        )
    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(50000),HomeState())

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DeleteShoppingItem -> {
                //TODO()
            }
            is HomeEvent.AddShoppingItem -> {
                //TODO()
            }
            is HomeEvent.MarkShoppingItem -> {
                //TODO()
            }
            HomeEvent.ShowDialogAddItem -> {
                //TODO()
            }
            HomeEvent.HideDialogAddItem -> {
                //TODO()
            }
            HomeEvent.ShowDialogEditCategory -> {
                //TODO()
            }
            HomeEvent.HideDialogEditCategory -> {
                //TODO()
            }
        }
    }
}