package domain.event

import data.local.model.CategoryList
import data.local.model.ShoppingItem
import data.local.model.ShoppingList

data class HomeState(
    val categories: List<CategoryList> = emptyList(),
    val shoppingLists: List<ShoppingList> = emptyList(),
    val shoppingItems: List<ShoppingItem> = emptyList(),

    val isEditingCategory: Boolean = false,
    val isAddingShoppingItem: Boolean = false,
    val isRemovingShoppingItem: Boolean = false,
    val isMarkingShoppingItem: Boolean = false,

    val categoryName: String = "",
    val shoppingListName: String = "",
    val shoppingItemName: String = "",
)
