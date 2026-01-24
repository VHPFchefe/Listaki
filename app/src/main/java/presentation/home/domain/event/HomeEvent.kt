package presentation.home.domain.event

import data.local.model.ShoppingItemDb
import presentation.home.domain.model.CategoryListUi
import presentation.home.domain.model.ShoppingListUi

sealed interface HomeEvent {

    // Category
    data class AddCategory(val categoryName: String): HomeEvent
    data class DeleteCategory(val category: CategoryListUi): HomeEvent
    data class ChangeCategoryName(val newCategoryName: String): HomeEvent
    object ShowDialogEditCategory: HomeEvent
    object DialogAddCategoryIsOpen: HomeEvent
    object HideDialogEditCategory: HomeEvent
    data class SelectCategory(val category: CategoryListUi): HomeEvent
    /*
    .
    .
    .
    .
    */
    // Shopping List
    data class CreateShoppingList(val idCategory: Int): HomeEvent
    data class DeleteShoppingList(val shoppingList: ShoppingListUi): HomeEvent
    data class SetShoppingListName(val shoppingListUi: ShoppingListUi, val shoppingListName: String): HomeEvent
    data class EditShoppingListTitle(val isEditing: Boolean): HomeEvent
    data class SelectShoppingList(val shoppingList: ShoppingListUi): HomeEvent
    /*
    .
    .
    .
    .
    */
    // Shopping Item
    data class DeleteShoppingItem(val shoppingItemDb: ShoppingItemDb): HomeEvent
    data class AddShoppingItem(val shoppingItemDb: ShoppingItemDb): HomeEvent
    data class MarkShoppingItem(val shoppingItemDb: ShoppingItemDb): HomeEvent
    data class ChangeShoppingItemName(val shoppingItemName: String): HomeEvent
    object ShowDialogAddItem: HomeEvent
    object HideDialogAddItem: HomeEvent
}