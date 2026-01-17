package presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import presentation.home.domain.event.HomeEvent
import presentation.home.domain.event.HomeState
import presentation.theme.AppColors
import presentation.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCategoryDialog(
    onEvent: (HomeEvent) -> Unit,
    state: HomeState
){
    var categoryName by remember { mutableStateOf(state.selectedCategory?.name?:"")}
    val isEditing = state.selectedCategory?.name != ""
    print("isEditing $isEditing")
    BasicAlertDialog(
        onDismissRequest = {
            println("onEvent(HomeEvent.HideDialogEditCategory)")
            onEvent(HomeEvent.HideDialogEditCategory)
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
        modifier = Modifier
            .background(AppColors.Transparent)
    ) {
        Surface(
            border = BorderStroke(2.dp, AppColors.Orange500),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .background(AppColors.Transparent)
        )  {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(AppColors.Transparent)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = AppColors.Black,
                    style = MaterialTheme.typography.titleMedium,
                    softWrap = true,
                    text = if(!isEditing)
                            "Criar uma categoria de compras"
                        else
                            "Editar listas de ${state.selectedCategory?.name}"
                )

                Text("Nome da categoria",
                    Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 16.dp)
                )
                TextField(
                    value = categoryName,
                    placeholder = {
                        Text(text = "Nome da categoria")
                    },
                    onValueChange = {
                        categoryName = it
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        showKeyboardOnFocus = true,

                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    if(isEditing) {
                        IconButton(
                            onClick = {
                                onEvent(HomeEvent.DeleteCategory(state.selectedCategory!!))
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = AppColors.Red500
                                )
                                Text(
                                    "Apagar categoria",
                                    fontWeight = FontWeight.Bold,
                                    color = AppColors.Red500
                                )
                            }
                        }
                    }
                    Button(
                        onClick = {
                            println("onEvent(HomeEvent.AddCategory(state.categoryName))")
                            if(isEditing) {
                                onEvent(HomeEvent.ChangeCategoryName(categoryName))
                            } else {
                                onEvent(HomeEvent.AddCategory(categoryName))
                            }
                        },
                        colors = ButtonColors(
                            containerColor = AppColors.Green500,
                            contentColor = AppColors.White,
                            disabledContainerColor = AppColors.Orange500,
                            disabledContentColor = AppColors.White
                        ),
                    ) {
                        Text("Salvar",fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}


@Preview(
    backgroundColor = 0xFFFDF5E6,
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_5"
)
@Composable
fun CreateCategoryDialogPreview(){
    CreateCategoryDialog(onEvent = {}, state = HomeState())
}
