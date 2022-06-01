package dev.hinaka.pokedex.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
      label = stringResource(id = R.string.home_pokedex),
      isSelected = currentRoute == POKEDEX_ROUTE,
      action = {
        navigateToPokedex()
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_move_dex),
      isSelected = currentRoute == MOVE_ROUTE,
      action = {
        navigateToMove()
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_ability_dex),
      isSelected = currentRoute == ABILITY_ROUTE,
      action = {
        navigateToAbility()
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_item_dex),
      isSelected = currentRoute == ITEM_ROUTE,
      action = {
        navigateToItem()
        closeDrawer()
      }
    )

    DrawerButton(
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
  label: String,
  isSelected: Boolean,
  action: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val colors = MaterialTheme.colors

  val textColor = if (isSelected) {
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
      Text(
        text = label,
        style = MaterialTheme.typography.body2,
        color = textColor,
      )
    }
  }
}