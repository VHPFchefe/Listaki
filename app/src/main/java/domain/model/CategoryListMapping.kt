package domain.model

import data.local.model.CategoryListDb
import data.local.model.ShoppingListDb
import domain.model.CategoryList
import domain.model.ShoppingItem
import domain.model.ShoppingList

fun List<ShoppingList>.toShoppingList(): List<ShoppingList> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            ShoppingList(
                name = it.name,
                shoppingItems = it.shoppingItems.toShoppingItem()
            )
        }
    }
}

fun List<ShoppingItem>.toShoppingItem(): List<ShoppingItem> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            ShoppingItem(
                name = it.name,
                quantity = it.quantity,
                unity = it.unity,
                isChecked = it.isChecked
            )
        }
    }
}
fun List<CategoryList>.toCategoryListUi(): List<presentation.home.domain.model.CategoryListUi> {
    return this.map {
        _root_ide_package_.presentation.home.domain.model.CategoryListUi(
            name = it.name,
            shoppingListUi = it.list.toShoppingListUi(),
            idCategory = it.idCategory
        )
    }
}

fun List<presentation.home.domain.model.CategoryListUi>.toCategoryListDb(): List<CategoryListDb> {
    return this.map {
        CategoryListDb(
            name = it.name,
            idCategory = it.idCategory
        )
    }
}
fun presentation.home.domain.model.CategoryListUi.toCategoryListDb(): CategoryListDb {
    return CategoryListDb(
            name = this.name,
            idCategory = this.idCategory
        )
}

fun List<CategoryListDb>.toCategoryList(): List<CategoryList> {
    return this.map {
        CategoryList(
            name = it.name,
            list = emptyList(),
            idCategory = it.idCategory
        )
    }

}

fun List<ShoppingList>.toShoppingListUi(): List<presentation.home.domain.model.ShoppingListUi> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            _root_ide_package_.presentation.home.domain.model.ShoppingListUi(
                name = it.name,
                shoppingItems = it.shoppingItems.toShoppingItemUi()
            )
        }
    }
}

fun List<ShoppingItem>.toShoppingItemUi(): List<presentation.home.domain.model.ShoppingItemUi> {
    return if(this.isEmpty()){
        emptyList()
    }else {
        this.map{
            _root_ide_package_.presentation.home.domain.model.ShoppingItemUi(
                name = it.name,
                quantity = it.quantity,
                unity = it.unity,
                isChecked = it.isChecked
            )
        }
    }
}
