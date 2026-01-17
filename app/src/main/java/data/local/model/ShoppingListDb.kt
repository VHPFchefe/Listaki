package data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_lists",
    foreignKeys = [ForeignKey(
        entity = CategoryListDb::class,
        parentColumns = ["id_category"],
        childColumns = ["id_category"],
        onDelete = CASCADE
    )]
)
data class ShoppingListDb(
    @ColumnInfo("id_shopping_list")
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("id_category")
    val idCategory: Int,

)
