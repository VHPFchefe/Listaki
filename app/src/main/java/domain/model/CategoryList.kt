package domain.model

data class CategoryList(
    val idCategory: Int?,
    val name: String,
    var list: List<ShoppingList> = emptyList()

)
