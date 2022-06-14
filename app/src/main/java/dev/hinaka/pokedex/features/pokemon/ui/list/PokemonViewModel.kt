package dev.hinaka.pokedex.features.pokemon.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hinaka.pokedex.features.pokemon.usecases.GetPokemonPagingUseCase
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
  private val getPokemonPagingUseCase: GetPokemonPagingUseCase,
) : ViewModel() {

  val pokemons = getPokemonPagingUseCase(20).cachedIn(viewModelScope)

}