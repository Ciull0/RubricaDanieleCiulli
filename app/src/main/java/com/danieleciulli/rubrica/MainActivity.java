package com.danieleciulli.rubrica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView fotoProfilo,btnEdit,btnDelete;
    TextView nome,cognome,cellulare,mail,indirizzo,sitoweb;
    Contatto info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        if(i != null){
            info =(Contatto) i.getSerializableExtra("info");
        }
        else{
            info = new Contatto("Nome e Cognome","Cellulare", "Email","Indirizzo", "Sito Web", "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");
        }



        fotoProfilo = findViewById(R.id.profilePicture);
        nome= findViewById(R.id.nome);
        cellulare= findViewById(R.id.telefono);
        mail= findViewById(R.id.mail);
        indirizzo= findViewById(R.id.indirizzo);
        sitoweb= findViewById(R.id.sitoweb);
        btnEdit=findViewById(R.id.btnEdit2);
        btnDelete=findViewById(R.id.btnDelete);

        Glide.with(this).load(info.getFotoProfilo()).into(fotoProfilo);
        nome.setText(info.getNome());
        cellulare.setText(info.getCellulare());
        mail.setText(info.getMail());
        indirizzo.setText(info.getIndirizzo());
        sitoweb.setText(info.getSitoweb());

        cellulare.setOnClickListener(this);
        mail.setOnClickListener(this);
        indirizzo.setOnClickListener(this);
        sitoweb.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        MainActivity m =(MainActivity) view.getContext();
        switch (view.getId()){
            case R.id.telefono:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+info.getCellulare()));

                //ora chiedo i permessi per chiamare
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new  String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }

                startActivity(intent);
                break;
            case R.id.mail:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{info.getMail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Weee");
                intent.putExtra(Intent.EXTRA_TEXT,"Weee ti sto mandando una maiò");

                startActivity(intent);
                break;
            case R.id.indirizzo:
                Uri MapsAddress = Uri.parse("geo:0,0?q="+Uri.encode(info.getIndirizzo()));
                intent = new Intent(Intent.ACTION_VIEW, MapsAddress);

                startActivity(intent);
                break;
            case R.id.sitoweb:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+info.getSitoweb()));

                startActivity(intent);
                break;
            case R.id.btnEdit2:
                Intent edit = new Intent(m,ModificaContatto.class);
                edit.putExtra("modificatoContatto",info);
                m.startActivity(edit);
                break;
            case R.id.btnDelete:
                Intent i = new Intent(m,listacontatti.class);
                DBManager.getInstance(getApplicationContext()).getContattiDAO().deleteContatto(info);
                this.startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int grantResult : grantResults){
            if (grantResult == PackageManager.PERMISSION_GRANTED){
                Uri uri = Uri.parse("tel:" + info.getCellulare());
                Intent chiamata = new Intent(Intent.ACTION_CALL, uri);
                chiamata.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(chiamata);
            }else{
                Log.e("TestChiamata","non chiama");
            }
        }
    }
}