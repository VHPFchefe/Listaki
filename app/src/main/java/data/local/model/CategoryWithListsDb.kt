package data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithListsDb (
    @Embedded val category: CategoryListDb,
    @Relation(
        parentColumn = "id_category",
        entityColumn = "id_category"
    )
    val shoppingListDbs: List<ShoppingListDb>
)