package com.danieleciulli.rubrica;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FirebaseManager {
    private static FirebaseFirestore dbCloud;
    boolean riuscito = false;
    List<Contatto> contattiperdavvero;


    public FirebaseManager(Context c) {
        this.riuscito = false;
        dbCloud = getInstance(c);
    }

    public static FirebaseFirestore getInstance(Context c){
        if(dbCloud == null){
            dbCloud = FirebaseFirestore.getInstance();
        }
        return dbCloud;
    }

    public List<Contatto> getContatti(String utente){

        DocumentReference docRef = dbCloud.collection(utente).document("rubrica");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    contattiperdavvero = new ArrayList<Contatto>();
                    if(document.exists()){
                        Log.d("firebaseMio","C'è documento   "+document.getData());

                        for (Object contattoSingolo: document.getData().values()){
                            contattiperdavvero.add( (Contatto) contattoSingolo);
                        }
                        Log.d("firebaseMio","C'è documentoList   "+contattiperdavvero);

                        for(Object contattoSingolo : contattiperdavvero){
                            Log.d("firebaseMio","C'è Contatto   "+contattoSingolo);

                            /*Class<?> contattoClass = contattoSingolo.getClass();

                            Field field = contattoClass.getField("nome");
                            String nameValue =(String) field.get(contattoSingolo);

                            field = contattoClass.getField("cellulare");
                            String telefonoValue =(String)  field.get(contattoSingolo);

                            field = contattoClass.getField("sitoweb");
                            String sitowebValue =(String)  field.get(contattoSingolo);

                            field = contattoClass.getField("mail");
                            String mailValue =(String)  field.get(contattoSingolo);

                            field = contattoClass.getField("indirizzo");
                            String indirizzoValue =(String)  field.get(contattoSingolo);

                            field = contattoClass.getField("id");
                            String idValue =(String)  field.get(contattoSingolo);

                            field = contattoClass.getField("fotoProfilo");
                            String fotoValue =(String)  field.get(contattoSingolo);

                            Contatto contattoTemp = new Contatto(idValue,nameValue,telefonoValue,mailValue,indirizzoValue,sitowebValue,fotoValue);
                            contattiperdavvero.add( contattoTemp);*/
                        }

                    }else {
                        Log.d("firebaseMio", "niente documento");
                    }
                }else{
                    Log.d("firebaseMio","qualcosa non funge",task.getException());
                }
            }
        });
        return contattiperdavvero;
    }

    public boolean saveAll(List<Contatto> contatti, String nomeUtente){
        Map<String, Contatto> contattiDbCloud = new HashMap<>();


        for(Contatto tmp : contatti){
            contattiDbCloud.put(String.valueOf(tmp.getId()),tmp);
        }


        dbCloud.collection(nomeUtente).document("rubrica")
                .set(contattiDbCloud, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("firebaseMio","caricati");
                        riuscito = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("firebaseMio","errore",e);
                    }
                });
        return riuscito;
    }

    public boolean deleteAll(){
        return false;
    }
}
