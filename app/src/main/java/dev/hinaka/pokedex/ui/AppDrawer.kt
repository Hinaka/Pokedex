package dev.hinaka.pokedex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.R
import dev.hinaka.pokedex.ui.PokedexDestinations.ABILITY_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.ITEM_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.MOVE_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.NATURE_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.POKEDEX_ROUTE

@Composable
fun AppDrawer(
  currentRoute: String,
  navigateToPokedex: () -> Unit,
  navigateToMove: () -> Unit,
  navigateToAbility: () -> Unit,
  navigateToItem: () -> Unit,
  navigateToNature: () -> Unit,
  closeDrawer: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.fillMaxSize()) {

    DrawerButton(
      icon = painterResource(id = R.drawable.home_pokedex),
      label = stringResource(id = R.string.home_pokedex),
      isSelected = currentRoute == POKEDEX_ROUTE,
      action = {
        navigateToPokedex()
        closeDrawer()
      }
    )

    DrawerButton(
      icon = painterResource(id = R.drawable.home_move),
      label = stringResource(id = R.string.home_move_dex),
      isSelected = currentRoute == MOVE_ROUTE,
      action = {
        navigateToMove()
        closeDrawer()
      }
    )

    DrawerButton(
      icon = painterResource(id = R.drawable.home_ability),
      label = stringResource(id = R.string.home_ability_dex),
      isSelected = currentRoute == ABILITY_ROUTE,
      action = {
        navigateToAbility()
        closeDrawer()
      }
    )

    DrawerButton(
      icon = painterResource(id = R.drawable.home_item),
      label = stringResource(id = R.string.home_item_dex),
      isSelected = currentRoute == ITEM_ROUTE,
      action = {
        navigateToItem()
        closeDrawer()
      }
    )

    DrawerButton(
      icon = painterResource(id = R.drawable.home_nature),
      label = stringResource(id = R.string.home_nature_dex),
      isSelected = currentRoute == NATURE_ROUTE,
      action = {
        navigateToNature()
        closeDrawer()
      }
    )
  }
}

@Composable
private fun DrawerButton(
  icon: Painter,
  label: String,
  isSelected: Boolean,
  action: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val colors = MaterialTheme.colors

  val iconTextColor = if (isSelected) {
    colors.primary
  } else {
    colors.onSurface.copy(alpha = 0.6f)
  }

  val backgroundColor = if (isSelected) {
    colors.primary.copy(alpha = 0.12f)
  } else {
    Color.Transparent
  }

  val surfaceModifier = modifier
    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    .fillMaxWidth()

  Surface(
    modifier = surfaceModifier,
    color = backgroundColor,
    shape = MaterialTheme.shapes.small
  ) {
    TextButton(
      onClick = action,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        DrawerIcon(
          icon = icon,
          isSelected = isSelected,
          contentDescription = label,
          tintColor = iconTextColor,
        )
        Spacer(Modifier.width(16.dp))
        Text(
          text = label,
          style = MaterialTheme.typography.body2,
          color = iconTextColor,
        )
      }
    }
  }
}

@Composable
fun DrawerIcon(
  icon: Painter,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  contentDescription: String? = null,
  tintColor: Color? = null,
) {
  val imageAlpha = if (isSelected) {
    1f
  } else {
    0.6f
  }

  val iconTintColor = tintColor ?: if (isSelected) {
    MaterialTheme.colors.primary
  } else {
    MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
  }

  Image(
    painter = icon,
    contentDescription = contentDescription,
    modifier = modifier,
    contentScale = ContentScale.Inside,
    colorFilter = ColorFilter.tint(iconTintColor),
    alpha = imageAlpha
  )
}