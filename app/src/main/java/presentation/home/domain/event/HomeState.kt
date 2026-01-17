package presentation.home.domain.event

import androidx.compose.runtime.Immutable
import presentation.home.domain.model.CategoryListUi
import presentation.home.domain.model.ShoppingItemUi
import presentation.home.domain.model.ShoppingListUi

@Immutable
data class HomeState(
    val categoriesUi: List<CategoryListUi> = emptyList(),
    val selectedCategory: CategoryListUi? = null,

    val isLoadingCategory: Boolean = false,
    val isEditingCategory: Boolean = false,
    val isDialogOpen: Boolean = false,
    val isAddingShoppingItem: Boolean = false,
    val isRemovingShoppingItem: Boolean = false,
    val isMarkingShoppingItem: Boolean = false,
    val categoryName: String = "",
    val shoppingListName: String = "",
    val shoppingItemName: String = ""
)
