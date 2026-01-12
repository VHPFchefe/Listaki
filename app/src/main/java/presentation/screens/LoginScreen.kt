package presentation.screens

import androidx.compose.animation.core.animateFloatAsState
import presentation.theme.AppColors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.R
import kotlinx.coroutines.delay
import presentation.theme.BungeeFamily

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var loginSucceeded by remember {mutableStateOf(false)}

    LaunchedEffect(key1 = Unit) {
        // CHAMAR A FUNÇÃO REAL DE LOGIN DO GOOGLE
        delay(1000) // Simulando uma espera de 3 segundos
        val success = true // Simulando que o login foi um sucesso
        loginSucceeded = success
    }

    val imageBrightness by animateFloatAsState(
        targetValue = if(loginSucceeded) 0.3f else 1.0f,
    )
    val isLabelClickHereEnabled = loginSucceeded


    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply {
                    setToScale(
                        redScale = imageBrightness,
                        greenScale = imageBrightness,
                        blueScale = imageBrightness,
                        alphaScale = 1f
                    )
                }
            ),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "Woman holding shopping bag"
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "NOME DO APP",
                fontFamily = BungeeFamily,
                fontSize = 46.sp,
                color = AppColors.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxWidth()
                    .padding(end = 22.dp,start = 22.dp)
                    .height(46.dp)

            )

            Text(
                text = "Criado por \n Vinícius Floriano",
                fontFamily = BungeeFamily,
                fontSize = 22.sp,
                color = AppColors.Orange500,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 28.dp,start = 28.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Clique aqui para começar!!!",
                fontFamily = BungeeFamily,
                fontSize = 46.sp,
                color = if(isLabelClickHereEnabled) AppColors.Orange500 else AppColors.Transparent,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable(
                        enabled = isLabelClickHereEnabled,
                        onClick = {
                            onLoginSuccess()
                        }
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen(
        onLoginSuccess = {}
    )
}