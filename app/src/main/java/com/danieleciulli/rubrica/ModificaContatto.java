package com.danieleciulli.rubrica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.*;

public class ModificaContatto extends AppCompatActivity implements View.OnClickListener {

    EditText nome, mail, indizzo, sito, telefono;
    CardView btnSave;
    Contatto info;
    Utente utenteCorrente;
    boolean nuovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_contatto);

        utenteCorrente = DBManager.getInstance(getApplicationContext()).getUtentiDAO().getUtente();

        Intent i = getIntent();
        if (i != null) {
            info = (Contatto) i.getSerializableExtra("nuovoContatto");
            if (info != null) {
                nuovo = true;
            } else {
                info = (Contatto) i.getSerializableExtra("modificatoContatto");
                nuovo = false;
            }
        } else {
            info = new Contatto("nome","cellulare","mail","indirizzo","sitoweb");
            nuovo = true;
        }

        nome = findViewById(R.id.nomeEdit);
        mail = findViewById(R.id.mailEdit);
        indizzo = findViewById(R.id.indirizzoEdit);
        sito = findViewById(R.id.sitowebEdit);
        telefono = findViewById(R.id.telefonoEdit);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        nome.setHint(info.getNome());
        mail.setHint(info.getMail());
        telefono.setHint(info.getCellulare());
        sito.setHint(info.getSitoweb());
        indizzo.setHint(info.getIndirizzo());


    }

    @Override
    public void onClick(View view) {
        boolean datiOk=true;
        boolean change=false;
        switch (view.getId()) {
            case R.id.btnSave:

                if(nome.getText().length() >= 2){
                    info.setNome(nome.getText().toString());
                    Log.e("save",nome.getText().toString());
                    change=true;
                }else if(info.getNome()=="nome"){
                    nome.setHint(R.string.nomeWarning);
                    nome.setHintTextColor(getColor(R.color.red));
                    Log.e("save","Errore Nome");
                    datiOk = false;
                }


                if(telefono.getText().length() >= 2){
                    info.setCellulare(telefono.getText().toString());
                    change=true;
                }else if (info.getCellulare()=="cellulare"){
                    Log.e("save","Errore Telefono");
                    telefono.setHint(R.string.telefonoWarning);
                    telefono.setHintTextColor(getColor(R.color.red));
                    datiOk = false;
                }


                if(indizzo.getText().length() >= 2){
                    info.setIndirizzo(indizzo.getText().toString());
                    change=true;
                }else if (info.getIndirizzo()=="indirizzo"){
                    indizzo.setHint(R.string.indirizzoWarning);
                    indizzo.setHintTextColor(getColor(R.color.red));
                    Log.e("save","Errorre Indirizzo");
                    datiOk = false;
                }


                if(mail.getText().length() >= 2){
                    info.setMail(mail.getText().toString());
                    change=true;
                }else if (info.getMail()=="mail"){
                    mail.setHint("Inserisci una Mail");
                    mail.setHintTextColor(getColor(R.color.red));
                    Log.e("save","Errore Mail");
                    datiOk = false;
                }


                if(sito.getText().length() >= 2){
                    info.setSitoweb(sito.getText().toString());
                    change=true;
                }else if (info.getSitoweb()=="sitoweb"){
                    sito.setHint(R.string.sitoWarning);
                    sito.setHintTextColor(getColor(R.color.red));
                    Log.e("save","Errore sito");
                    datiOk = false;
                }


                info.setFotoProfilo("https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");


                if(datiOk && change){
                    if (nuovo) {
                        Intent i = new Intent(this, listacontatti.class);
                        DBManager.getInstance(getApplicationContext()).getContattiDAO().insertContatto(info);
                        i.putExtra("utente",utenteCorrente);
                        this.startActivity(i);
                    } else {
                        Intent i = new Intent(this, listacontatti.class);
                        DBManager.getInstance(getApplicationContext()).getContattiDAO().updateContatto(info);
                        i.putExtra("utente",utenteCorrente);
                        this.startActivity(i);
                    }
                }
                if(!change){
                    Intent i = new Intent(this, listacontatti.class);
                    i.putExtra("utente",utenteCorrente);
                    this.startActivity(i);
                }


        }
    }
}