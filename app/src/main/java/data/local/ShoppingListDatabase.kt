package data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.model.CategoryList
import data.local.model.ShoppingItem
import data.local.model.ShoppingList
import data.local.model.User

@Database(
    entities = [
       User::class,
       CategoryList::class,
       ShoppingList::class,
       ShoppingItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
}