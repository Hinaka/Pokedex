package dev.hinaka.pokedex.ui.pokedex

import androidx.compose.runtime.Composable
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.hinaka.pokedex.data.remote.PokemonRemoteDataSource
import dev.hinaka.pokedex.data.remote.PokemonService
import dev.hinaka.pokedex.data.repositories.PokemonDataRepository
import dev.hinaka.pokedex.ui.pokedex.usecases.GetPokemonsUseCase
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Composable
fun PokedexRoute() {

  val contentType = MediaType.parse("application/json")
  val json = Json {
    ignoreUnknownKeys = true
  }
  val retrofit = Retrofit.Builder()
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(json.asConverterFactory(contentType!!))
    .build()

  val pokemonService = retrofit.create(PokemonService::class.java)
  val pokemonRemoteDataSource = PokemonRemoteDataSource(pokemonService)
  val pokemonRepository = PokemonDataRepository(pokemonRemoteDataSource)
  val getPokemonsUseCase = GetPokemonsUseCase(pokemonRepository)
  val viewModel = PokemonListViewModel(getPokemonsUseCase)

  PokemonListScreen(viewModel)
}