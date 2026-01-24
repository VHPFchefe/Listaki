package domain.model

data class CategoryList(
    val idCategory: Int?,
    val name: String,
    var shoppingLists: List<ShoppingList> = emptyList()
)
