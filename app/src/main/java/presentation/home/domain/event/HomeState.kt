package presentation.home.domain.event

import androidx.compose.runtime.Immutable
import presentation.home.domain.model.CategoryListUi
import presentation.home.domain.model.ShoppingItemUi
import presentation.home.domain.model.ShoppingListUi

@Immutable
data class HomeState(
    val categoriesUi: List<CategoryListUi> = emptyList(),
    val categoryName: String = "",
    val selectedCategory: CategoryListUi? = null,
    val isLoadingCategory: Boolean = false,
    val isEditingCategory: Boolean = false,
    val isDialogCategoryOpen: Boolean = false,

    val selectedShoppingList: ShoppingListUi? = null,
    val shoppingListName: String = "",
    val isEditingShoppingListTitle: Boolean = false,

    val shoppingItemName: String = ""
)
