package data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithLists (
    @Embedded val category: CategoryList,
    @Relation(
        parentColumn = "id_category",
        entityColumn = "id_category"
    )
    val shoppingLists: List<ShoppingList>
)