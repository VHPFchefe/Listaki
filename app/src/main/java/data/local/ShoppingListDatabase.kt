package data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.model.CategoryListDb
import data.local.model.ShoppingItemDb
import data.local.model.ShoppingListDb
import data.local.model.UserDb

@Database(
    entities = [
       UserDb::class,
       CategoryListDb::class,
       ShoppingListDb::class,
       ShoppingItemDb::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
}