package presentation.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import presentation.theme.AppColors

@Composable
fun ButtonCustomizable(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconSize: Dp = 24.dp,
    text: String,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
)
{
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(AppColors.Transparent),
        contentPadding = PaddingValues(horizontal = 26.dp,vertical = 16.dp),
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .size(iconSize)
            ,
            imageVector = icon,
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