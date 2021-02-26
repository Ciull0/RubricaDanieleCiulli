package com.danieleciulli.rubrica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContattiAdapter extends RecyclerView.Adapter<ListaContattiViewHolder> {
    private List<Contatto> contatti;
    private LayoutInflater inflater;

    public ContattiAdapter(Context c, List<Contatto> contatti) {
        this.contatti = contatti;
        inflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public ListaContattiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //crea view del layout

        View myView = inflater.inflate(R.layout.contatto_lista, viewGroup, false);
        return new ListaContattiViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContattiViewHolder listaContattiViewHolder, int i){
        //recupera i dati per la posizione
        String nome = contatti.get(i).getNome();
        String foto = contatti.get(i).getFotoProfilo();


        if(i%2 ==0){
            listaContattiViewHolder.cardView.setCardBackgroundColor(listaContattiViewHolder.cardView.getResources().getColor(R.color.white,null));
        }else{
            listaContattiViewHolder.cardView.setCardBackgroundColor(listaContattiViewHolder.cardView.getResources().getColor(R.color.grey,null));

        }

        listaContattiViewHolder.contatto=contatti.get(i);
        listaContattiViewHolder.nome.setText(nome);
        Glide.with(listaContattiViewHolder.cardView.getContext()).load(foto).into(listaContattiViewHolder.fotoProfilo);
    }

    @Override
    public int getItemCount() {
        return contatti.size();
    }
}
