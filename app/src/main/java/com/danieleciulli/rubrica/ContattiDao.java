package com.danieleciulli.rubrica;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContattiDao {
    @Insert
    void insertContatto(Contatto contatto);
    @Insert
    void insertContatti(List<Contatto> contatti);
    @Update
    void updateContatto(Contatto contatto);
    @Delete
    void deleteContatto(Contatto contatto);
    @Query("DELETE from Contatto")
    void deleteAll();
    @Query("SELECT * from Contatto")
    List<Contatto> getContatti();
}
