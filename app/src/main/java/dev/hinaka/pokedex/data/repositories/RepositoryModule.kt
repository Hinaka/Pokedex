package dev.hinaka.pokedex.data.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.hinaka.pokedex.data.repositories.PokemonDataRepository
import dev.hinaka.pokedex.domain.repositories.PokemonRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

  @Binds
  abstract fun bindPokemonRepository(repository: PokemonDataRepository): PokemonRepository

}