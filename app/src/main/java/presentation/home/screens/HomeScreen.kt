package presentation.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.home.components.CarouselCard
import presentation.home.domain.event.HomeEvent
import presentation.home.domain.event.HomeState
import presentation.home.domain.model.CategoryListUi
import presentation.theme.AppColors
import presentation.theme.BungeeFamily

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    println("Category is empty: ${state.categoriesUi.isEmpty()}")

    Column {
        TopAppBar()
        LazyColumn {
            items(state.categoriesUi) { category ->
                CarouselCard(category, state, onEvent)
            }
            item {
                CarouselCard(CategoryListUi(null,"",emptyList()), state, onEvent)
            }
        }
    }
}

@Composable
fun TopAppBar(){
    Column (
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(top = 32.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.weight(0.75f))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(0.25f)
                    .padding(end = 16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Account",
                    modifier = Modifier
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Account",
                    modifier = Modifier
                )
            }
        }
        Text(
            text = "NOME DO APP",
            fontFamily = BungeeFamily,
            fontSize = 26.sp,
            color = AppColors.Orange500,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 16.dp,top = 8.dp)
                .fillMaxWidth()
        )
    }
}

