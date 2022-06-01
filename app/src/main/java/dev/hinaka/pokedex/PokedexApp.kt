package dev.hinaka.pokedex

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import dev.hinaka.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.launch

@Composable
fun PokedexApp() {
  PokedexTheme {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalDrawer(
      drawerContent = {
        AppDrawer(closeDrawer = {
          coroutineScope.launch { drawerState.close() }
        })
      },
      drawerState = drawerState,
    ) {
    }
  }
}