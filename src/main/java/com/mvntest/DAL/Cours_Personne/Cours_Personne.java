package com.mvntest.DAL.Cours_Personne;

public class Cours_Personne {
    
    private int id_personne;
    private int id_cours;
    private int annee;

    public Cours_Personne() {
        
    }


    public Cours_Personne(int id_personne, int id_cours, int annee) {
        this.id_personne = id_personne;
        this.id_cours = id_cours;
        this.annee = annee;
    }

    public int getId_personne() {
        return id_personne;
    }
    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }
    public int getId_cours() {
        return id_cours;
    }
    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }


    @Override
    public String toString() {
        return "Cours_Personne [id_personne=" + id_personne + ", id_cours=" + id_cours + ", annee=" + annee + "]";
    }

}
