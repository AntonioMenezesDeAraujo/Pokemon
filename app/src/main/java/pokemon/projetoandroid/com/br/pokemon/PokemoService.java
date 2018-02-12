package pokemon.projetoandroid.com.br.pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by antonio on 12/02/18.
 */

public interface PokemoService {

    @GET("pokemon-list.json")
    Call<List<Pokemon>> listPokemon();

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);
}
