package allanksr.com.datastore_example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkTop,
    primaryVariant = darkCenter,
    onPrimary = darkBottom,

    background = bgDarkColor,
    onBackground = textDarkTheme
)

private val LightColorPalette = lightColors(
    primary = lightTop,
    primaryVariant = lightCenter,
    onPrimary = lightBottom,

    background = bgLightColor,
    onBackground = textLightTheme
)
@Composable
fun DataStoreExampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}