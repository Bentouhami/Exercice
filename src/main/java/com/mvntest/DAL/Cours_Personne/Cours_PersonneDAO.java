package com.mvntest.DAL.Cours_Personne;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

/**
 * Cette interface représente un objet d'accès aux données pour les cours des personnes.
 */
public interface Cours_PersonneDAO extends DAO<Cours_Personne> {
    
    /**
     * Crée la table Cours_Personne dans la base de données.
     * 
     * @throws SQLException si une erreur SQL survient
     */
    public void createCoursPersonneTB()throws SQLException;

    /**
     * Insère un cours pour une personne dans la base de données.
     * 
     * @param personNom Type String: le nom de la personne
     * @param personPrenom Type String: le prénom de la personne
     * @param coursNom Type String: le nom du cours
     * @param annee Type int: l'année du cours
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    public int insert(String personNom,
                        String personPrenom,
                        String coursNom,
                        int annee) throws SQLException;
    Cours_Personne get(int id_personne, int id_cours, int annee) throws SQLException;
    int insert(int id_section, int id_cours, int annee) throws SQLException;
    int update (int id_personne, int id_cours, int annee) throws SQLException;

}
