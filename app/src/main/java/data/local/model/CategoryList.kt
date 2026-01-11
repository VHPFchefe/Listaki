package data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "category_list"
)
data class CategoryList(
    @ColumnInfo("id_category")
    @PrimaryKey(autoGenerate = true)
    val idCategory: Int?,

    @ColumnInfo("name")
    val name: String,
)
