package dev.hinaka.pokedex.data.remote

import dev.hinaka.pokedex.data.responses.pokemon.GetPokemonResponse
import dev.hinaka.pokedex.data.responses.pokemon.GetPokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
  @GET("pokemon")
  suspend fun getPokemons(): GetPokemonsResponse

  @GET("pokemon/{name}")
  suspend fun getPokemon(@Path("name") name: String): GetPokemonResponse
}