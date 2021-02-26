package com.danieleciulli.rubrica;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseManager {
    private static FirebaseFirestore dbCloud;
    boolean riuscito = false;

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

    public boolean getContatti(String utente){
        return false;
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
