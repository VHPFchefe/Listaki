package data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithItems(
    @Embedded val list: ShoppingList,
    @Relation(
        parentColumn = "id_shopping_list",
        entityColumn = "id_shopping_list"
    ) val items: List<ShoppingItem>
)
