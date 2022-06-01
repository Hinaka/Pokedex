package dev.hinaka.pokedex

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

@Composable
fun AppDrawer(
  closeDrawer: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.fillMaxSize()) {
    DrawerButton(
      label = stringResource(id = R.string.home_pokedex),
      action = {
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_move_dex),
      action = {
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_ability_dex),
      action = {
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_item_dex),
      action = {
        closeDrawer()
      }
    )

    DrawerButton(
      label = stringResource(id = R.string.home_nature_dex),
      action = {
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