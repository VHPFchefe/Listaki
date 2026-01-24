package presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import presentation.home.domain.event.HomeEvent
import presentation.home.domain.event.HomeState
import presentation.home.domain.model.CategoryListUi
import presentation.home.domain.model.ShoppingItemUi
import presentation.home.domain.model.ShoppingListUi
import presentation.theme.AppColors

@Composable
fun CarouselCard(
    category: CategoryListUi,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
){
    if(state.isEditingCategory && state.selectedCategory == category){
        CreateCategoryDialog(state = state, onEvent = onEvent)
    }
    Column (
        modifier = Modifier
            .background(AppColors.Transparent)
        ,
    ){
        Button(
            colors = ButtonDefaults.buttonColors(AppColors.Transparent),
            onClick = {
                println("onEvent(HomeEvent.ShowDialogEditCategory)")
                onEvent(HomeEvent.SelectCategory(category))
                onEvent(HomeEvent.ShowDialogEditCategory)
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp)
                    ,
                    text = if(category.name == "")
                        "Criar uma categoria de compras"
                    else
                        "Editar listas de ${category.name}",
                    color = AppColors.Black,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Add",
                    tint = AppColors.Black
                )
            }
        }

        if(category.shoppingListUi.isEmpty()){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 26.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if(category.name == "")
                        "Você ainda não tem nenhuma lista de compras. Que tal criar uma?"
                        else
                        "Você ainda não tem nenhuma categoria!",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = AppColors.Black.copy(alpha = 0.6f)
                )
                if(category.name != "") {
                    ButtonCustomizable(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        icon = Icons.Default.AddCircle,
                        iconSize = 52.dp,
                        text = "",
                        onClick = {
                            onEvent(HomeEvent.CreateShoppingList(category.idCategory?:-1))
                        }
                    )
                }
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(items = category.shoppingListUi) { shoppingList ->
                    ListItemCard(
                        shoppingListUi = shoppingList,
                        modifier = Modifier.fillParentMaxWidth(fraction = 0.8f),
                        state = state,
                        onEvent = onEvent
                    )
                }
                item(){
                    ButtonCustomizable(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(top = 52.dp)
                        ,
                        icon = Icons.Default.AddCircle,
                        iconSize = 52.dp,
                        text = "",
                        onClick = {
                            onEvent(HomeEvent.CreateShoppingList(category.idCategory?:-1))
                        }
                    )
                }
            }
        }
    }
}





@Preview(
    backgroundColor = 0xFFFDF5E6,
    showBackground = true,
    showSystemUi = true
)
@Composable

fun CarouselCardPreviewWithCategoryEmpty(){
    CarouselCard(
        CategoryListUi(
            null,
            "Preview",
            emptyList()
        ),
        HomeState(),
        {}
    )
}

@Preview(
    backgroundColor = 0xFFFDF5E6,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CarouselCardPreviewWithoutCategory(){
    CarouselCard(
        CategoryListUi(
            null,
            "",
            emptyList()
        ),
        HomeState(),
        {}
    )
}


@Preview(
    backgroundColor = 0xFFFDF5E6,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CarouselCardPreviewWithItems(){
    val shoppingItems: List<ShoppingItemUi> = List(4) {
        ShoppingItemUi(
            idShoppingItem = it,
            name = "Item",
            quantity = 2,
            unity = "unity",
            isChecked = it % 2 == 0
        )
    }
    val shoppingList: List<ShoppingListUi> = List(2) {
        ShoppingListUi(
            idShoppingList = it,
            name = "Preview",
            shoppingItems = shoppingItems,
            idCategory = null
        )
    }
    CarouselCard(CategoryListUi(null,"Preview",shoppingList), HomeState(), {})
}


@Preview(
    backgroundColor = 0xFFFDF5E6,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CarouselCardPreviewWithOneItem(){
    val shoppingItems: List<ShoppingItemUi> = List(4) {
        ShoppingItemUi(
            idShoppingItem = it,
            name = "Item",
            quantity = 2,
            unity = "unity",
            isChecked = it % 2 == 0
        )
    }
    val shoppingList: List<ShoppingListUi> = List(1) {
        ShoppingListUi(
            idShoppingList = it,
            name = "Preview",
            shoppingItems = emptyList(),
            idCategory = null
        )
    }
    CarouselCard(CategoryListUi(null,"Preview",shoppingList), HomeState(), {})
}