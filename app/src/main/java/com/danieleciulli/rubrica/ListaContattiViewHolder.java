package com.danieleciulli.rubrica;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ListaContattiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView nome;
    public ImageView fotoProfilo;
    public Contatto contatto;
    public CardView cardView, btnEdit;

    public ListaContattiViewHolder(@NonNull View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.nome_lista);
        fotoProfilo = itemView.findViewById(R.id.foto_lista);
        cardView = itemView.findViewById(R.id.contattoLista);
        btnEdit = itemView.findViewById(R.id.btnEdit);

        nome.setOnClickListener(this);
        fotoProfilo.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listacontatti m =(listacontatti) view.getContext();
        switch (view.getId()){
            case R.id.nome_lista:
            case R.id.foto_lista:
                Intent i = new Intent(m,MainActivity.class);
                i.putExtra("info",contatto);
                m.startActivity(i);
                break;
            case R.id.btnEdit:
                Intent edit = new Intent(m,ModificaContatto.class);
                edit.putExtra("modificatoContatto",contatto);
                m.startActivity(edit);
                break;
        }

    }
}
