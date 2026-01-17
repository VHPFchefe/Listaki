package data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import data.local.model.CategoryListDb
import data.local.model.ShoppingItemDb
import data.local.model.ShoppingListDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Transaction
    @Insert
    suspend fun insertCategoryList(categoryList: CategoryListDb)
    @Transaction
    @Update
    suspend fun updateCategoryList(categoryList: CategoryListDb)
    @Transaction
    @Delete
    suspend fun deleteCategoryList(categoryList: CategoryListDb)
    @Transaction
    @Insert
    suspend fun insertShoppingList(shoppingList: ShoppingListDb)
    @Transaction
    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingListDb)

    @Transaction
    @Query("SELECT * FROM category_list")
    fun getCategoryLists(): Flow<List<CategoryListDb>>

    @Transaction
    @Query("SELECT * FROM shopping_lists WHERE id_category = :categoryId")
    fun getShoppingListsByCategory(categoryId: Int?): Flow<List<ShoppingListDb>>

    @Transaction
    @Query("SELECT * FROM shopping_items WHERE id_shopping_list = :listId")
    fun getItemsByList(listId: Int?): Flow<List<ShoppingItemDb>>
}