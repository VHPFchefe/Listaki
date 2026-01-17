package data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithItemsDb(
    @Embedded val list: ShoppingListDb,
    @Relation(
        parentColumn = "id_shopping_list",
        entityColumn = "id_shopping_list"
    ) val items: List<ShoppingItemDb>
)
