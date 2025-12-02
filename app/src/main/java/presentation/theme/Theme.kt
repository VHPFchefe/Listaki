package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.room.util.copy

class Theme {
    private val colorTheme = presentation.theme.Color

    private val shoppingLightColors = lightColorScheme(
        primary = colorTheme.Orange500,
        onPrimary = colorTheme.White,
        primaryContainer = colorTheme.Orange600,
        onPrimaryContainer = colorTheme.White,

        secondary = colorTheme.Green500,
        onSecondary = colorTheme.White,
        secondaryContainer = colorTheme.Green400,
        onSecondaryContainer = colorTheme.Black,

        tertiary = colorTheme.Yellow500,
        onTertiary = colorTheme.Black,

        background = colorTheme.Cream,
        onBackground = colorTheme.Slate800,

        surface = colorTheme.CreamSurface,
        onSurface = colorTheme.Slate800,
        surfaceVariant = Color(0xFFFDE68A).copy(alpha = 0.3f),
        onSurfaceVariant = colorTheme.Slate600,

        error = colorTheme.Red500,
        onError = colorTheme.White,

        outline = colorTheme.Slate400
    )

    private val shoppingDarkColors = darkColorScheme(
        primary = colorTheme.Orange500,
        onPrimary = colorTheme.White,
        primaryContainer = colorTheme.Orange600,
        onPrimaryContainer = colorTheme.White,

        secondary = colorTheme.Green400,
        onSecondary = colorTheme.Black,

        tertiary = colorTheme.Yellow500,
        onTertiary = colorTheme.Black,

        background = colorTheme.Teal700,
        onBackground = Color(0xFFF0FDF4),

        surface = Color(0xFF1E293B),
        onSurface = Color(0xFFF0FDF4),
        surfaceVariant = Color(0xFF134E4A),
        onSurfaceVariant = Color(0xFF94A3B8),

        error = colorTheme.Red500,
        onError = colorTheme.White,

        outline = Color(0xFF22C55E).copy(alpha = 0.5f)
    )

    @Composable
    fun ShoppingTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        val colorScheme = if (darkTheme) {
            shoppingDarkColors
        } else {
            shoppingLightColors
        }

        MaterialTheme(
            colorScheme = colorScheme,
            //typography = shoppingTypography,
            //shapes = shoppingShapes,
            content = content
        )
    }
}

