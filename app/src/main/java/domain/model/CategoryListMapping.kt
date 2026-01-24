package domain.model

import data.local.model.CategoryListDb
import data.local.model.CategoryWithAllData
import data.local.model.ShoppingItemDb
import data.local.model.ShoppingListDb
import data.local.model.ShoppingListWithItemsDb
import presentation.home.domain.model.CategoryListUi
import presentation.home.domain.model.ShoppingItemUi
import presentation.home.domain.model.ShoppingListUi

fun  List<CategoryWithAllData>.toCategoryListFromDb(): List<CategoryList> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        return this.map {
            CategoryList(
                name = it.category.name,
                shoppingLists = it.shoppingLists.toShoppingListFromDb(),
                idCategory = it.category.idCategory
            )
        }
    }
}
/*
.
.
.
 */
fun  List<ShoppingListWithItemsDb>.toShoppingListFromDb(): List<ShoppingList> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            ShoppingList(
                name = it.shoppingList.name,
                shoppingItems = it.shoppingItems.toShoppingItemFromDb(),
                idShoppingList = it.shoppingList.id,
                idCategory = it.shoppingList.idCategory
            )
        }
    }
}
/*
.
.
.
 */
fun List<ShoppingItemDb>.toShoppingItemFromDb(): List<ShoppingItem> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            ShoppingItem(
                idShoppingItem = it.id,
                name = it.name,
                quantity = it.quantity,
                unity = it.unity,
                isChecked = it.isChecked
            )
        }
    }
}
/*
.
.
.
 */
fun List<CategoryList>.toCategoryListUi(): List<CategoryListUi> {
    return this.map {
        CategoryListUi(
            name = it.name,
            shoppingListUi = it.shoppingLists.toShoppingListUi(),
            idCategory = it.idCategory
        )
    }
}
fun List<CategoryListDb>.toCategoryList(): List<CategoryList> {
    return this.map {
        CategoryList(
            name = it.name,
            shoppingLists = emptyList(),
            idCategory = it.idCategory
        )
    }

}
fun CategoryListUi.toCategoryListDb(): CategoryListDb {
    return CategoryListDb(
        name = this.name,
        idCategory = this.idCategory
    )
}
/*
.
.
.
 */
fun List<ShoppingList>.toShoppingListUi(): List<ShoppingListUi> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            ShoppingListUi(
                name = it.name,
                shoppingItems = it.shoppingItems.toShoppingItemUi(),
                idShoppingList = it.idShoppingList,
                idCategory = it.idCategory
            )
        }
    }
}
fun List<ShoppingListDb>.toShoppingList(): List<ShoppingList> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            ShoppingList(
                name = it.name,
                shoppingItems = emptyList(),
                idShoppingList = it.id,
                idCategory = it.idCategory
            )
        }
    }
}
fun ShoppingListUi.toShoppingListDb(): ShoppingListDb {
    return ShoppingListDb(
        id = idShoppingList,
        name = name,
        idCategory = idCategory?:-1
    )
}
/*
.
.
.
 */
fun List<ShoppingItem>.toShoppingItemUi(): List<ShoppingItemUi> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            ShoppingItemUi(
                idShoppingItem = it.idShoppingItem,
                name = it.name,
                quantity = it.quantity,
                unity = it.unity,
                isChecked = it.isChecked
            )
        }
    }
}
