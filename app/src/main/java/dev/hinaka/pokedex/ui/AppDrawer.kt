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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.R

@Composable
fun AppDrawer(
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
      action = {
        navigateToPokedex()
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_move_dex),
      action = {
        navigateToMove()
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_ability_dex),
      action = {
        navigateToAbility()
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_item_dex),
      action = {
        navigateToItem()
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_nature_dex),
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
  action: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val surfaceModifier = modifier
    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    .fillMaxWidth()
  Surface(
    modifier = surfaceModifier,
    shape = MaterialTheme.shapes.small
  ) {
    TextButton(
      onClick = action,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = label,
        style = MaterialTheme.typography.body2
      )
    }
  }
}