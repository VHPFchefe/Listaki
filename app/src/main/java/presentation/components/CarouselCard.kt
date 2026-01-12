package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import domain.event.HomeEvent
import domain.event.HomeState
import org.intellij.lang.annotations.JdkConstants
import presentation.theme.AppColors

@Composable
fun CarouselCard(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
){
    val categoryName = state.categoryName

    Column (
        modifier = Modifier
            .background(AppColors.Transparent)
    ){
        Button(
            colors = ButtonDefaults.buttonColors(AppColors.Transparent),
            onClick = {
                println("onEvent(HomeEvent.ShowDialogEditCategory)")
                onEvent(HomeEvent.ShowDialogEditCategory)
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ){
            Text(
                text = "Editar listas de ${if(categoryName == "") "compras" else categoryName}",
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
        if(state.shoppingLists.isEmpty()){
            println("state.shoppingLists.isEmpty()")
            EmptyState()
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(state.shoppingLists) { shoppingList ->
                    ListItemCard(
                        state = state,
                        shoppingList = shoppingList,
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
