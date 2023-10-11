package com.mvntest.DAL.Cours;

public class Cours {

    private int id = 0;
    private int id_section;
    private String nom;
    
    public Cours(int id, int id_section, String nom) {
        this.id = id;
        this.id_section = id_section;
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_section() {
        return id_section;
    }
    public void setId_section(int id_section) {
        this.id_section = id_section;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() {
        return "Cours [id=" + id + ", id_section=" + id_section + ", nom=" + nom + "]";
    }
    
}
