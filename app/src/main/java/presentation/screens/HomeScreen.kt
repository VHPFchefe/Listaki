package presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import domain.model.ShoppingList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.json.Json
import presentation.components.CarouselCard
import presentation.theme.AppColors
import presentation.theme.BungeeFamily
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.viewmodel.HomeViewModel

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,dpi=480"
)
@Composable
fun HomeScreenPreview() {
    // Mock
    val context = LocalContext.current
    val jsonString = context.assets.open("shopping_lists_mock.json")
        .bufferedReader()
        .use { it.readText() }
    val shoppingList = Json.decodeFromString<List<ShoppingList>>(jsonString)
    HomeScreenContent(shoppingList)
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val shoppingList by viewModel.shoppingLists.collectAsStateWithLifecycle()
    HomeScreenContent(shoppingList)
}

@Composable
fun HomeScreenContent(shoppingList: List<ShoppingList>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar()
        ContentList(shoppingList)
    }
}

@Composable
fun ContentList(shoppingList: List<ShoppingList>?){
    LazyColumn {
        // temporary
        items(10){
            // First Category
            CarouselCard(
                shoppingList,
                onEditClick = {}
            )
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

