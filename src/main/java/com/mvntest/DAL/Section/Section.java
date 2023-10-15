package com.mvntest.DAL.Section;

public class Section {

    private int id;
    private String nom;

    public Section() {
    }

    public Section(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Section(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Section [id=" + id + ", nom=" + nom + "]";
    }

}
