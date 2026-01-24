package domain.model

data class ShoppingItem (
    val idShoppingItem: Int?,
    val name: String,
    val quantity: Int,
    val unity: String,
    val isChecked: Boolean
)

