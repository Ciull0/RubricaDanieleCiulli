package com.danieleciulli.rubrica;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Contatto implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo
    private String nome;
    @ColumnInfo
    private String cellulare;
    @ColumnInfo
    private String mail;
    @ColumnInfo
    private String indirizzo;
    @ColumnInfo
    private String sitoweb;
    @ColumnInfo
    private String fotoProfilo;

    public Contatto() {
        this.nome = "";
        this.cellulare = "";
        this.mail = "";
        this.indirizzo = "";
        this.sitoweb = "";
        this.fotoProfilo = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png";
    }


    public Contatto(String nome, String cellulare, String mail, String indirizzo, String sitoweb) {
        this.nome = nome;
        this.cellulare = cellulare;
        this.mail = mail;
        this.indirizzo = indirizzo;
        this.sitoweb = sitoweb;
        this.fotoProfilo = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png";
    }

    public Contatto(String nome, String cellulare, String mail, String indirizzo, String sitoweb, String fotoProfilo) {
        this.nome = nome;
        this.cellulare = cellulare;
        this.mail = mail;
        this.indirizzo = indirizzo;
        this.sitoweb = sitoweb;
        this.fotoProfilo = fotoProfilo;
    }

    public void setId(int id) { this.id = id; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setSitoweb(String sitoweb) {
        this.sitoweb = sitoweb;
    }

    public void setFotoProfilo(String fotoProfilo) {
        this.fotoProfilo = fotoProfilo;
    }


    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getCellulare() {
        return cellulare;
    }

    public String getMail() {
        return mail;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getSitoweb() {
        return sitoweb;
    }

    public String getFotoProfilo() {
        return fotoProfilo;
    }
}
