package com.danieleciulli.rubrica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class listacontatti extends AppCompatActivity implements View.OnClickListener {

    List<Contatto> contattoList;
    RecyclerView recyclerView;
    CardView btnAdd,btnUpload;
    Utente infoUtente;
    FirebaseManager dbCloud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacontatti);

        Intent i = getIntent();

        infoUtente = (Utente) i.getSerializableExtra("utente");

        if(infoUtente == null){
            i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }

        dbCloud = new FirebaseManager(getApplicationContext());

        btnAdd = findViewById(R.id.btnAdd);
        btnUpload=findViewById(R.id.btnUpload);

        btnAdd.setOnClickListener(this);
        btnUpload.setOnClickListener(this);

        contattoList = DBManager.getInstance(getApplicationContext()).getContattiDAO().getContatti();


        recyclerView = findViewById(R.id.lista);
        ContattiAdapter adapter = new ContattiAdapter(this,contattoList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                Contatto tmp = new Contatto("nome","cellulare","mail","indirizzo","sitoweb");
                listacontatti m =(listacontatti) view.getContext();
                Intent i = new Intent(m,ModificaContatto.class);
                i.putExtra("nuovoContatto",tmp);
                m.startActivity(i);
                break;
            case R.id.btnUpload:
                dbCloud.saveAll(contattoList,"daniele");
        }
    }
}