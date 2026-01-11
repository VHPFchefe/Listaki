package domain.event

import data.local.model.ShoppingItem

sealed interface HomeEvent {
    data class DeleteShoppingItem(val shoppingItem: ShoppingItem): HomeEvent
    data class AddShoppingItem(val shoppingItem: ShoppingItem): HomeEvent
    data class MarkShoppingItem(val shoppingItem: ShoppingItem): HomeEvent

    object ShowDialogAddItem: HomeEvent
    object HideDialogAddItem: HomeEvent

    object ShowDialogEditCategory: HomeEvent
    object HideDialogEditCategory: HomeEvent
}