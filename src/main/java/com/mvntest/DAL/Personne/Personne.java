package com.mvntest.DAL.Personne;

public class Personne {
    
    private int id = 0;
    private int id_status;
    private String nom, prenom;


    public Personne() {
    }


    public Personne(int id ,int id_status, String nom, String prenom) {
        this.id = id;
        this.id_status = id_status;
        this.nom = nom;
        this.prenom = prenom;
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
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    @Override
    public String toString() {
        return "Personne [id=" + id + ", id_status=" + id_status + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}
