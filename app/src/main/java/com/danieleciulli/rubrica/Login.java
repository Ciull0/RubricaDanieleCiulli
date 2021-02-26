package com.danieleciulli.rubrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button login,register;
    EditText utente,password;
    Utente utenteCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        utenteCorrente = DBManager.getInstance(getApplicationContext()).getUtentiDAO().getUtente();

        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);

        utente = findViewById(R.id.login);
        password = findViewById(R.id.password);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(utenteCorrente == null){ ;
            login.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        String name = utente.getText().toString();
        String psw = password.getText().toString();
        switch (view.getId()){
            case R.id.btnLogin:
                if(name.equals(utenteCorrente.getUser()) && psw.equals(utenteCorrente.getPassword())){
                    Intent i = new Intent(getApplicationContext(),listacontatti.class);
                    i.putExtra("utente",utenteCorrente);
                    startActivity(i);
                }
                break;
            case R.id.btnRegister:
                if(name.length() > 1 && psw.length() > 1){
                    Utente tmp = new Utente(name,psw);
                    DBManager.getInstance(getApplicationContext()).getUtentiDAO().deleteAll();
                    DBManager.getInstance(getApplicationContext()).getUtentiDAO().insertUtente(tmp);
                    utenteCorrente = DBManager.getInstance(getApplicationContext()).getUtentiDAO().getUtente();



                    Intent i = new Intent(getApplicationContext(),listacontatti.class);
                    i.putExtra("utente",utenteCorrente);
                    startActivity(i);
                }
                break;
        }
    }
}