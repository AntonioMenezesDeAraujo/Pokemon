package pokemon.projetoandroid.com.br.pokemon;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public static PokemoService api = null;

    private final int ACTION_BUSCA = 1;


    @BindView(R.id.lista)
    RecyclerView lista;

    private List<Pokemon> listPokemon = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        carregarAPI();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        buscar();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lista.setLayoutManager(mLayoutManager);
        lista.setItemAnimator(new DefaultItemAnimator());

        lista.setAdapter(new PokemonAdapter(listPokemon,this));
    }

    public void buscar(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, ACTION_BUSCA);
        }
        else {
            Call<List<Pokemon>> request = MainActivity.api.listPokemon();
            Response<List<Pokemon>> response = null;
            try {
                response = request.execute();
                listPokemon = response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ACTION_BUSCA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    buscar();
                    return;
                }
                break;
            }
        }

        Toast.makeText(this, "Sem essa permissão o app não irá funcionar. Tente novamente.", Toast.LENGTH_LONG).show();
    }

    private void carregarAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/cheeaun/repokemon/master/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainActivity.api = retrofit.create(PokemoService.class);
    }


}
