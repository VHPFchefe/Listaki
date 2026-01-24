package data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoriesWithListsDb(
    @Embedded
    val category: CategoryListDb,
    @Relation(
        parentColumn = "id_category",
        entityColumn = "id_category"
    )
    val shoppingLists: List<ShoppingListDb>
)

data class ShoppingListWithItemsDb(
    @Embedded
    val shoppingList: ShoppingListDb,
    @Relation(
        parentColumn = "id_shopping_list",
        entityColumn = "id_shopping_list"
    )
    val shoppingItems: List<ShoppingItemDb>
)

data class CategoryWithAllData(
    @Embedded
    val category: CategoryListDb,
    @Relation(
        entity = ShoppingListDb::class,
        parentColumn = "id_category",
        entityColumn = "id_category"
    )
    val shoppingLists: List<ShoppingListWithItemsDb>

)
