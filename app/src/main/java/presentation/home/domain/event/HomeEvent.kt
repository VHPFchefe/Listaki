package presentation.home.domain.event

import data.local.model.ShoppingItemDb
import presentation.home.domain.model.CategoryListUi

sealed interface HomeEvent {
    data class DeleteShoppingItem(val shoppingItemDb: ShoppingItemDb): HomeEvent
    data class AddShoppingItem(val shoppingItemDb: ShoppingItemDb): HomeEvent
    data class MarkShoppingItem(val shoppingItemDb: ShoppingItemDb): HomeEvent
    data class AddCategory(val categoryName: String): HomeEvent
    data class DeleteCategory(val category: CategoryListUi): HomeEvent
    data class ChangeCategoryName(val newCategoryName: String): HomeEvent
    data class ChangeShoppingListName(val shoppingListName: String): HomeEvent
    data class ChangeShoppingItemName(val shoppingItemName: String): HomeEvent
    object ShowDialogAddItem: HomeEvent
    object HideDialogAddItem: HomeEvent
    object ShowDialogEditCategory: HomeEvent
    object DialogIsOpen: HomeEvent
    object HideDialogEditCategory: HomeEvent
    data class SelectCategory(val category: CategoryListUi): HomeEvent
}