package presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import presentation.home.domain.event.HomeEvent
import presentation.home.domain.event.HomeState
import presentation.home.domain.model.CategoryListUi
import presentation.theme.AppColors

@Composable
fun CarouselCard(
    category: CategoryListUi,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
){
    if(state.isEditingCategory){
        println("CreateCategoryDialog(state, onEvent)")
        CreateCategoryDialog(state = state, onEvent = onEvent)
        onEvent(HomeEvent.DialogIsOpen)
    }
    Column (
        modifier = Modifier
            .background(AppColors.Transparent)
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
            Text(
                text = if(category.name == "")
                    "Criar uma categoria de compras"
                else
                    "Editar listas de ${category.name}",
                color = AppColors.Black,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Add",
                tint = AppColors.Black
            )
        }

        if(category.shoppingListUi.isEmpty()){
            println("state.shoppingListDbs.isEmpty()")
            EmptyState()
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(items = category.shoppingListUi) { shoppingList ->
                    ListItemCard(
                        shoppingListUi = shoppingList,
                        modifier = Modifier.fillParentMaxWidth(fraction = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyState(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 26.dp),
        verticalArrangement = Arrangement.Top

    ) {
        Text(
            text = "Você ainda não tem nenhuma lista de compras. Que tal criar uma?",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = AppColors.Black.copy(alpha = 0.6f)
        )
    }
}
