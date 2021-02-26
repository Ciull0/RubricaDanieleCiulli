package com.danieleciulli.rubrica;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contatto.class, Utente.class},version = 3)
public abstract class DBManager extends RoomDatabase {
    private static final String DB_NOME = "ContattiDB";
    private static DBManager instance;


    public static DBManager getInstance(Context c){
        if(instance == null){
            instance = Room.databaseBuilder(c,DBManager.class,DB_NOME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }




    public abstract ContattiDao getContattiDAO();
    public abstract UtentiDao getUtentiDAO();
}
