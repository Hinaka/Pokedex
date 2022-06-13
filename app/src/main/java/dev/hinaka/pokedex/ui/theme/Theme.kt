package dev.hinaka.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.hinaka.pokedex.domain.models.type.Type
import dev.hinaka.pokedex.domain.models.type.Type.BUG
import dev.hinaka.pokedex.domain.models.type.Type.DARK
import dev.hinaka.pokedex.domain.models.type.Type.DRAGON
import dev.hinaka.pokedex.domain.models.type.Type.ELECTRIC
import dev.hinaka.pokedex.domain.models.type.Type.FAIRY
import dev.hinaka.pokedex.domain.models.type.Type.FIGHTING
import dev.hinaka.pokedex.domain.models.type.Type.FIRE
import dev.hinaka.pokedex.domain.models.type.Type.FLYING
import dev.hinaka.pokedex.domain.models.type.Type.GHOST
import dev.hinaka.pokedex.domain.models.type.Type.GRASS
import dev.hinaka.pokedex.domain.models.type.Type.GROUND
import dev.hinaka.pokedex.domain.models.type.Type.ICE
import dev.hinaka.pokedex.domain.models.type.Type.NORMAL
import dev.hinaka.pokedex.domain.models.type.Type.POISON
import dev.hinaka.pokedex.domain.models.type.Type.PSYCHIC
import dev.hinaka.pokedex.domain.models.type.Type.ROCK
import dev.hinaka.pokedex.domain.models.type.Type.STEEL
import dev.hinaka.pokedex.domain.models.type.Type.UNKNOWN
import dev.hinaka.pokedex.domain.models.type.Type.WATER

private val DarkColorPalette = darkColors(
  primary = Red200,
  primaryVariant = Red700,
)

private val LightColorPalette = lightColors(
  primary = Red500,
  primaryVariant = Red700,

  /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PokedexTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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

fun Colors.ofType(type: Type) = when (type) {
  NORMAL -> Color(0xFFA0A29F)
  FIGHTING -> Color(0xFFD3425F)
  FLYING -> Color(0xFFA1BBEC)
  POISON -> Color(0xFFB763CF)
  GROUND -> Color(0xFFDA7C4D)
  ROCK -> Color(0xFFC9BB8A)
  BUG -> Color(0xFF92BC2C)
  GHOST -> Color(0xFF5F6DBC)
  STEEL -> Color(0xFF5695A3)
  FIRE -> Color(0xFFFBA54C)
  WATER -> Color(0xFF539DDF)
  GRASS -> Color(0xFF5FBD58)
  ELECTRIC -> Color(0xFFF2D94E)
  PSYCHIC -> Color(0xFFFA8581)
  ICE -> Color(0xFF75D0C1)
  DRAGON -> Color(0xFF0C69C8)
  DARK -> Color(0xFF595761)
  FAIRY -> Color(0xFFEE90E6)
  UNKNOWN -> Color(0xFFCBD4DD)
}