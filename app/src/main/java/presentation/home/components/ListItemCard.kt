package presentation.home.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.home.domain.event.HomeEvent
import presentation.home.domain.event.HomeState
import presentation.home.domain.model.CategoryListUi
import presentation.home.domain.model.ShoppingItemUi
import presentation.home.domain.model.ShoppingListUi
import presentation.theme.AppColors

@Composable
fun ListItemCard(
    shoppingListUi: ShoppingListUi,
    modifier: Modifier = Modifier,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    println("ListItemCard Rebuild")
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    var newShoppingListName by remember {
        mutableStateOf(state.selectedShoppingList?.name?: state.shoppingListName)
    }
    var textValidation = ""
       Surface(
        modifier = modifier
            .background(AppColors.Transparent)
            .clip(MaterialTheme.shapes.extraLarge)
    ){
        Column (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(AppColors.CreamSurface)
                .border(
                    width = 3.dp,
                    color = AppColors.Orange500,
                    shape = MaterialTheme.shapes.extraLarge
                )

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(state.isEditingShoppingListTitle && state.selectedShoppingList == shoppingListUi) {
                    println("state.selectedShoppingList ${state.selectedShoppingList}")
                    TextField(
                        modifier = Modifier
                            .padding(start = 26.dp,top = 26.dp)
                            .fillMaxWidth(0.7f)
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                if(
                                    !it.isFocused &&
                                    newShoppingListName != "" &&
                                    newShoppingListName != shoppingListUi.name
                                ) {
                                    println("SetShoppingListName $newShoppingListName")
                                    println("on ${state.selectedShoppingList}")
                                    textValidation = newShoppingListName
                                    onEvent(
                                        HomeEvent
                                            .SetShoppingListName(
                                                state.selectedShoppingList,
                                                newShoppingListName
                                            )
                                    )

                                    onEvent(
                                        HomeEvent
                                            .EditShoppingListTitle(false)
                                    )
                                } else {
                                    newShoppingListName = shoppingListUi.name
                                    if(
                                        (!it.isFocused && newShoppingListName == shoppingListUi.name && textValidation != "") ||
                                        (!it.isFocused && newShoppingListName == "")
                                    ) {
                                        println("newShoppingListName: $newShoppingListName")
                                        Toast.makeText(
                                            context,
                                            "O nome da lista não foi alterado",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            },
                        value = newShoppingListName,
                        placeholder = {
                            Text(text = "Digite o nome da Lista")
                        },
                        onValueChange = {
                            if (it.length <= 25) {
                                newShoppingListName = it
                                textValidation = "Validated"
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            showKeyboardOnFocus = true,
                            capitalization = KeyboardCapitalization.Unspecified,
                            keyboardType = KeyboardType.Ascii,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = AppColors.Transparent,
                            unfocusedContainerColor = if(textValidation != "")
                                AppColors.RedErrorBackground else AppColors.Transparent,
                            disabledContainerColor = AppColors.Transparent
                        ),
                        keyboardActions = KeyboardActions {
                            println("done")
                            focusManager.clearFocus()
                        }

                    )
                    LaunchedEffect(Unit) {
                        focusRequester.requestFocus()
                    }

                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.73f)
                        ,

                    ){
                        Text(
                            modifier = Modifier
                                .weight(.9f)
                                .padding(start = 26.dp)
                                .clickable(
                                    onClick = {
                                        onEvent(
                                            HomeEvent
                                                .SelectShoppingList(
                                                    shoppingListUi
                                                )
                                        )
                                        onEvent(
                                            HomeEvent
                                                .EditShoppingListTitle(
                                                    true
                                                )
                                        )
                                    }
                                )
                            ,
                            text = shoppingListUi.name,
                            color = AppColors.Black,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold
                            ),
                            fontSize = 28.sp,
                        )
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                            ,
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit name",
                            tint = AppColors.Orange500,

                        )
                    }
                }
                ButtonCustomizable(
                    modifier = Modifier
                        .padding(top = 26.dp)
                    ,
                    Icons.Default.Delete,
                    iconSize = 26.dp,
                    text = "",
                    contentDescription = "Remove the list",
                    onClick = {
                        onEvent(HomeEvent.DeleteShoppingList(shoppingListUi))
                    }
                )
            }


            if(shoppingListUi.shoppingItems.isEmpty()) {

            } else {
                shoppingListUi.shoppingItems.forEach {item ->
                    ListItem(item)
                }
            }

            ButtonsBar()
        }
    }
}

@Composable
fun ButtonsBar() {
    Row (
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ){

        ButtonCustomizable(
            modifier = Modifier,
            Icons.Default.AddCircle,
            iconSize = 24.dp,
            text = "Add an item to the list",
            contentDescription = "Add an item to the list",
            onClick = {}
        )
    }
}

@Composable
fun ListItem(item: ShoppingItemUi){
    Column (
        modifier = Modifier
            .padding(horizontal = 26.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = AppColors.Gray,
                    start = Offset(0f,y),
                    end = Offset(size.width,y),
                    strokeWidth = strokeWidth
                )
            }
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ){
            Text(
                text = item.name,
                color = AppColors.Black,
                style = MaterialTheme.typography.bodySmall,
                //fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${item.quantity} ${item.unity}",
                color = AppColors.Black,
                style = MaterialTheme.typography.bodySmall,
                //fontSize = 20.sp,
                modifier = Modifier.padding(end = 26.dp)
            )

            Checkbox(
                checked = item.isChecked,
                onCheckedChange = { /*TODO*/ },
                colors = CheckboxDefaults.colors(
                    checkedColor = AppColors.Orange500,
                    uncheckedColor = AppColors.Black
                ),
                modifier = Modifier.padding(end = 5.dp)
            )
        }
    }
}

@Preview(
    backgroundColor = 0xFFFDF5E6,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ListItemCardPreview() {
    val shoppingItems: List<ShoppingItemUi> = List(7) {
        ShoppingItemUi(
            idShoppingItem = it,
            name = "Item",
            quantity = 2,
            unity = "unity",
            isChecked = it % 2 == 0
        )
    }
    ListItemCard(
        ShoppingListUi(
            idShoppingList = 1,
            name = "Preview extended in two rows",
            shoppingItems = shoppingItems,
            idCategory = null
        ),
        modifier = Modifier,
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
fun EditingListNamePreview() {
    val shoppingItems: List<ShoppingItemUi> = List(7) {
        ShoppingItemUi(
            idShoppingItem = it,
            name = "Item",
            quantity = 2,
            unity = "unity",
            isChecked = it % 2 == 0
        )
    }
    val shoppingListUi = ShoppingListUi(
        idShoppingList = 1,
        name = "Preview",
        shoppingItems = shoppingItems,
        idCategory = null
    )

    ListItemCard(
        shoppingListUi,
        modifier = Modifier,
        HomeState(
            selectedShoppingList = shoppingListUi,
            isEditingShoppingListTitle = true
        ),
        onEvent = {}
    )
}

