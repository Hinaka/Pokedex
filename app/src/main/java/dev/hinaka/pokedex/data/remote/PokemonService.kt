package dev.hinaka.pokedex.data.remote

import dev.hinaka.pokedex.data.responses.pokemon.GetPokemonResponse
import dev.hinaka.pokedex.data.responses.pokemon.GetPokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

  @GET("pokemon")
  suspend fun getPokemons(
    @Query("offset") offset: Int,
    @Query("limit") limit: Int,
  ): GetPokemonsResponse

  @GET("pokemon/{name}")
  suspend fun getPokemon(@Path("name") name: String): GetPokemonResponse
}