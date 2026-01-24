package presentation.home.domain.model

data class ShoppingListUi(
    val idShoppingList: Int?,
    val idCategory: Int?,
    val name: String,
    val shoppingItems: List<ShoppingItemUi> = emptyList()
)
