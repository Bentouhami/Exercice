package com.mvntest.DAL.Personne;

public class Personne {
    
    private int id = 0;
    private int id_status;
    private String nom, prenoms;


    public Personne() {
    }


    public Personne(int id_status, String nom, String prenoms) {
        this.id_status = id_status;
        this.nom = nom;
        this.prenoms = prenoms;
    }

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_status() {
        return id_status;
    }
    public void setId_status(int id_status) {
        this.id_status = id_status;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenoms() {
        return prenoms;
    }
    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }
    
    @Override
    public String toString() {
        return "Personne [id=" + id + ", id_status=" + id_status + ", nom=" + nom + ", prenoms=" + prenoms + "]";
    }
}
