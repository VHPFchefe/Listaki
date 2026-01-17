package presentation.home.domain.model

data class ShoppingListUi(
    val name: String,
    val shoppingItems: List<ShoppingItemUi> = emptyList()
)
