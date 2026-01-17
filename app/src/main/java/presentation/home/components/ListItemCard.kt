package presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.home.domain.model.CategoryListUi
import presentation.home.domain.model.ShoppingItemUi
import presentation.home.domain.model.ShoppingListUi
import presentation.theme.AppColors

@Composable
fun ListItemCard(
    shoppingListUi: ShoppingListUi,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .background(AppColors.Transparent)
            .clip(MaterialTheme.shapes.extraLarge)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.CreamSurface)
                .border(
                    width = 3.dp,
                    color = AppColors.Orange500,
                    shape = MaterialTheme.shapes.extraLarge
                )
        ){
            // Title
            Text(
                text = shoppingListUi.name,
                color = AppColors.Black,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(start = 26.dp, top = 26.dp)
            )

            if(shoppingListUi.shoppingItems.isEmpty()) {
                EmptyState()
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
            Icons.Default.Delete,
            "Remove",
            "Remove an item to the list"
        )

        ButtonCustomizable(
            Icons.Default.AddCircle,
            "Add",
            "Add an item to the list"
        )
    }
}

@Composable
fun ButtonCustomizable(icon: ImageVector,text: String, contentDescription: String? = null) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(AppColors.Transparent),
        contentPadding = PaddingValues(horizontal = 26.dp,vertical = 16.dp),
        modifier = Modifier
    ) {
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = AppColors.Orange500
        )
        Text(
            text = text,
            color = AppColors.Black,
            //fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp)
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
                modifier = Modifier
            )
        }
    }
}

