package data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import data.local.model.CategoryList
import data.local.model.CategoryWithLists
import data.local.model.ListWithItems
import data.local.model.ShoppingItem
import data.local.model.ShoppingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Transaction
    @Insert
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    @Transaction
    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingList)

    @Transaction
    @Query("SELECT * FROM category_list")
    fun getCategoryLists(): Flow<List<CategoryList>>

    @Transaction
    @Query("SELECT * FROM shopping_lists WHERE id_category = :categoryId")
    fun getShoppingListsByCategory(categoryId: Int): Flow<List<ShoppingList>>

    @Transaction
    @Query("SELECT * FROM shopping_items WHERE id_shopping_list = :listId")
    fun getItemsByList(listId: Int): Flow<List<ShoppingItem>>
}