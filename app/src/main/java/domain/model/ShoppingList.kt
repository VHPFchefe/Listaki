package domain.model

data class ShoppingList(
     val name: String,
     val shoppingItems: List<ShoppingItem> = emptyList()
)
