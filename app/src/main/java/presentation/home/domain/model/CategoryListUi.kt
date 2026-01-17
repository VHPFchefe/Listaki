package presentation.home.domain.model

data class CategoryListUi(
    val idCategory: Int?,
    val name: String,
    val shoppingListUi: List<ShoppingListUi> = emptyList()
)
