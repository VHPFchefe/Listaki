package presentation.home.domain.viewmodel

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.local.ShoppingListDao
import data.local.model.CategoryListDb
import data.local.model.ShoppingListDb
import presentation.home.domain.event.HomeState
import presentation.home.domain.event.HomeEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import domain.model.toCategoryListDb
import domain.model.toCategoryListFromDb
import domain.model.toCategoryListUi
import domain.model.toShoppingListDb
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(
    private val dao: ShoppingListDao
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    private val _categoriesWithAllData = dao.getCategoryWithAllData()

    val state = combine(
        _state,
        _categoriesWithAllData
    ) {state, categoriesWithAllData ->
        state.copy(
            categoriesUi = categoriesWithAllData.toCategoryListFromDb().toCategoryListUi()
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HomeState()
    )


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SelectShoppingList -> {

                _state.update { it.copy(
                    selectedShoppingList = event.shoppingList,
                    shoppingListName = event.shoppingList.name
                )}

                println("select shopping list: ${event.shoppingList.name}")
            }
            is HomeEvent.EditShoppingListTitle -> {
                _state.update { it.copy(
                    isEditingShoppingListTitle = event.isEditing,
                )}
            }
            is HomeEvent.SetShoppingListName -> {
                if(event.shoppingListUi.name == event.shoppingListName) {
                    return
                }
                if(event.shoppingListName == "") {
                    return
                }
                val shoppingListDb = ShoppingListDb(
                    id = event.shoppingListUi.idShoppingList,
                    idCategory = event.shoppingListUi.idCategory?:-1,
                    name = event.shoppingListName
                )

                _state.update { it.copy(
                    shoppingListName = ""
                )}

                viewModelScope.launch {
                    try {
                        println("dao.updateShoppingList")
                        dao.updateShoppingList(shoppingListDb)
                    } catch (e: Exception) {
                        e("HomeViewModel", "onEvent: $e")
                        println("ROOM_ERROR: ${e.message}")
                    }
                }
            }
            is HomeEvent.DeleteShoppingList -> {
                viewModelScope.launch {
                    dao.deleteShoppingList(
                        shoppingList = event.shoppingList.toShoppingListDb()
                    )
                }
            }
            is HomeEvent.CreateShoppingList -> {
                viewModelScope.launch {
                    dao.insertShoppingList(
                        ShoppingListDb(
                            name = "Nova lista de compras",
                            idCategory = event.idCategory,
                            id = null
                        )
                    )
                }
            }

            is HomeEvent.SelectCategory -> {
                _state.update { it.copy(
                    selectedCategory = event.category
                )}
            }
            is HomeEvent.DialogAddCategoryIsOpen -> {
                _state.update { it.copy(
                    isDialogCategoryOpen = true
                )}
            }
            is HomeEvent.DeleteShoppingItem -> {
                //TODO()
            }
            is HomeEvent.AddShoppingItem -> {
                //TODO()
            }
            is HomeEvent.MarkShoppingItem -> {
                //TODO()
            }
            is HomeEvent.ShowDialogAddItem -> {
                //TODO()
            }
            is HomeEvent.HideDialogAddItem -> {
                //TODO()
            }
            is HomeEvent.ShowDialogEditCategory -> {
                _state.update { it.copy(
                    isEditingCategory = true
                )}
            }
            is HomeEvent.HideDialogEditCategory -> {
                _state.update { it.copy(
                    isEditingCategory = false
                )}
            }
            is HomeEvent.DeleteCategory -> {
                viewModelScope.launch {
                    dao.deleteCategoryList(
                        categoryList = event.category.toCategoryListDb()
                    )
                }
                _state.update { it.copy(
                    isEditingCategory = false
                )}
            }
            is HomeEvent.AddCategory -> {
                val categoryName = event.categoryName

                if(categoryName.isBlank()) {
                    println("categoryName.isBlank()")
                    return
                }

                viewModelScope.launch {
                    dao.insertCategoryList(
                        categoryList = CategoryListDb(null, categoryName)
                    )
                }

                _state.update { it.copy(
                    isEditingCategory = false,
                    categoryName = ""
                )}
            }
            is HomeEvent.ChangeCategoryName -> {
                val categoryName = event.newCategoryName

                if(categoryName.isBlank()) {
                    println("categoryName.isBlank()")
                    return
                }

                viewModelScope.launch {
                    dao.updateCategoryList(
                        categoryList = CategoryListDb(_state.value.selectedCategory?.idCategory, categoryName)
                    )
                }

                _state.update { it.copy(
                    isEditingCategory = false,
                    categoryName = ""
                )}
            }

            is HomeEvent.ChangeShoppingItemName -> TODO()
        }
    }
}