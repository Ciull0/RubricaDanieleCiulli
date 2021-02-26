package com.danieleciulli.rubrica;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UtentiDao {
    @Insert
    void insertUtente(Utente utente);
    @Update
    void updateUtente(Utente utente);
    @Delete
    void deleteUtente(Utente utente);
    @Query("DELETE from Utente")
    void deleteAll();
    @Query("select * from Utente where user = :user limit 1")
    Utente selectUtenteFromUser(String user);
    @Query("select * from Utente limit 1")
    Utente getUtente();
}
