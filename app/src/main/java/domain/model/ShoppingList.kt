package domain.model

data class ShoppingList(
    val idShoppingList: Int?,
    val idCategory: Int?,
    val name: String,
    val shoppingItems: List<ShoppingItem> = emptyList()
)
