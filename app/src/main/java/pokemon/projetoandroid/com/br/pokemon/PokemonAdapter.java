package pokemon.projetoandroid.com.br.pokemon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by antonio on 12/02/18.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{

    private List<Pokemon> lista;
    private Context context;

    public PokemonAdapter(List<Pokemon> lista, Context context){
        this.lista = lista;
        this.context = context;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false);
        PokemonViewHolder holder = new PokemonViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        Picasso.with(context).load(lista.get(position).getThumbnailImage()).into(holder.imagem);
        holder.descricao.setText(lista.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        ImageView imagem;
        TextView descricao;

        public PokemonViewHolder(View view) {
            super(view);
            imagem = (ImageView) view.findViewById(R.id.idImagem);
            descricao = (TextView) view.findViewById(R.id.idDescricao);
        }

    }
}
