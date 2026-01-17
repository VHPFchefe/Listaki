package presentation.home.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.local.ShoppingListDao
import data.local.model.CategoryListDb
import domain.model.CategoryList
import presentation.home.domain.event.HomeState
import presentation.home.domain.event.HomeEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import domain.model.toCategoryList
import domain.model.toCategoryListDb
import domain.model.toCategoryListUi

class HomeViewModel(
    private val dao: ShoppingListDao
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    var categories: List<CategoryList> = emptyList()

    private fun loadCategoryLists() {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        isLoadingCategory = true
                    )
                }
                dao.getCategoryLists().collect { listCategoryLists ->
                    categories = listCategoryLists.toCategoryList()
                    _state.update {
                        it.copy(
                            isLoadingCategory = false,
                            categoriesUi = categories.toCategoryListUi()
                        )
                    }
                }
            } catch (e: Exception) {
                println("Error: $e")
            }
        }
    }

    init {
        println("init")
        loadCategoryLists()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SelectCategory -> {
                _state.update { it.copy(
                    selectedCategory = event.category
                )}
            }
            is HomeEvent.DialogIsOpen -> {
                _state.update { it.copy(
                    isDialogOpen = true
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
            is HomeEvent.ChangeShoppingItemName -> {
                //TODO()
            }
            is HomeEvent.ChangeShoppingListName -> {
                //TODO()
            }
        }
    }
}