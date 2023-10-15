package com.mvntest.DAL.Cours_Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cours_PersonneDAOEmplt implements Cours_PersonneDAO {

    private final String RESULTSET_ERROR = "SQL ResultSet Error: \n";
    private final String PREPAREDSTATEMENT_ERROR = "SQL PreparedStatement Error: \n";
    private Connection conn;
    private PreparedStatement ps;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    /**
     * Constructeur de la classe Cours_PersonneDAOEmplt.
     * 
     * @throws SQLException si une erreur SQL survient
     */
    public Cours_PersonneDAOEmplt(Connection conn) throws SQLException {
        this.conn = conn;
    }

    /**
     * Récupère un objet Cours_Personne à partir de l'année.
     * 
     * @param annee Type int: l'année à partir de laquelle récupérer l'objet
     *              Cours_Personne
     * @return Type Cours_Personne: l'objet Cours_Personne correspondant à l'année
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public Cours_Personne get(int annee) throws SQLException {
        String sqlQuery = "SELECT id_personne, id_cours, annee FROM cours_personne WHERE  annee = ?";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, annee);
            try (ResultSet rs = this.ps.executeQuery()) {
                return rs.next() ? new Cours_Personne(rs.getInt("id_personne"),
                        rs.getInt("id_cours"),
                        rs.getInt("annee")) : null;
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return null;
    }

    /**
     * Supprime un objet Cours_Personne de la base de données.
     * 
     * @param annee Type int: l'année de l'objet à supprimer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int delete(int annee) throws SQLException {
        String sqlQuery = "DELETE FROM cours_personne WHERE annee = ?";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, annee);
            int rowAffected = this.ps.executeUpdate();
            return rowAffected > 0 ? rowAffected : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    /**
     * Récupère tous les objets Cours_Personne.
     * 
     * @return Type ArrayList<Cours_Personne>: la liste de tous les objets
     *         Cours_Personne
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public ArrayList<Cours_Personne> getAll() throws SQLException {

        String sqlQuery = "SELECT id_personne, id_cours , annee FROM cours_personne";
        ArrayList<Cours_Personne> coursPersonneListe = new ArrayList<Cours_Personne>();
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            try (ResultSet rs = this.ps.executeQuery()) {

                while (rs.next()) {
                    coursPersonneListe.add(new Cours_Personne(rs.getInt("id_personne"),
                            rs.getInt("id_cours"),
                            rs.getInt("annee")));
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return coursPersonneListe;
    }

    /**
     * Enregistre un objet Cours_Personne dans la base de données.
     * 
     * @param cours_Personne Type Cours_Personne: l'objet à enregistrer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int save(Cours_Personne cours_Personne) throws SQLException {

        return cours_Personne.getAnnee() == 0 ? insert(cours_Personne) : update(cours_Personne);
    }

    /**
     * Inserts a new Cours_Personne object into the database.
     * 
     * @param nom      Type String: the last name of the person
     * @param prenom   Type String: the first name of the person
     * @param coursNom Type String: the name of the course
     * @param annee    Type int: the year of the course
     * @return Type int: the number of rows affected by the operation
     * @throws SQLException if an SQL error occurs
     */
    @Override
    public int insert(String nom, String prenom, String coursNom, int annee) throws SQLException {

        String sqlQuery = "INSERT INTO cours_personne(id_personne, id_cours, annee)"
                + "VALUES("
                + "(SELECT id FROM personne WHERE nom = ? AND prenom = ? ),"
                + "(SELECT id FROM cours WHERE nom = ?),"
                + "?"
                + ")";

        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setString(1, nom);
            this.ps.setString(2, prenom);
            this.ps.setString(3, coursNom);
            this.ps.setInt(4, annee);

            int affectedRows = this.ps.executeUpdate();
            return affectedRows > 0 ? affectedRows : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    /**
     * Inserts a new Cours_Personne object into the database.
     * 
     * @param c_p Type Cours_Personne: the Cours_Personne object to insert
     * @return Type int: the number of rows affected by the operation
     * @throws SQLException if an SQL error occurs
     */
    @Override
    public int insert(Cours_Personne c_p) throws SQLException {
        String sqlQuery = "INSERT INTO cours_personne( id_personne, id_cours, annee )"
                + " VALUES( ?,?,? )";

        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setInt(1, c_p.getId_personne());
            this.ps.setInt(2, c_p.getId_cours());
            this.ps.setInt(3, c_p.getAnnee());

            int affectedRows = this.ps.executeUpdate();
            return affectedRows > 0 ? affectedRows : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    /**
     * Inserts a new Cours_Personne object into the database.
     * 
     * @param id_section Type int: the id of the section
     * @param id_cours   Type int: the id of the course
     * @param annee      Type int: the year of the course
     * @return Type int: the number of rows affected by the operation
     * @throws SQLException if an SQL error occurs
     */
    @Override
    public int insert(int id_personne, int id_cours, int annee) throws SQLException {
        String sqlQuery = "INSERT INTO cours_personne( id_personne, id_cours, annee )"
                + " VALUES( ?,?,? )";

        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setInt(1, id_personne);
            this.ps.setInt(2, id_cours);
            this.ps.setInt(3, annee);

            int affectedRows = this.ps.executeUpdate();
            return affectedRows > 0 ? affectedRows : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    /**
     * Met à jour un objet Cours_Personne dans la base de données.
     * 
     * @param cp Type Cours_Personne: l'objet à mettre à jour
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int update(Cours_Personne cp) throws SQLException {
        return update(cp.getId_personne(), cp.getId_cours(), cp.getAnnee());
    }

    /**
     * Supprime un objet Cours_Personne de la base de données.
     * 
     * @param cPersonne Type Cours_Personne: l'objet à supprimer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int delete(Cours_Personne cPersonne) throws SQLException {
        return delete(cPersonne.getAnnee());
    }

    /**
     * Récupère un objet Cours_Personne dans la base de données.
     * 
     * @param id_personne Type int: l'identifiant de la personne
     * @param id_cours    Type int: l'identifiant du cours
     * @param annee       Type int: l'année
     * @return Type Cours_Personne: l'objet Cours_Personne correspondant aux
     *         paramètres donnés
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public Cours_Personne get(int id_personne, int id_cours, int annee) throws SQLException {
        String sqlQuery = "SELECT id_personne, id_cours, annee FROM cours_personne WHERE id_personne = ? AND id_cours = ? AND annee =  ?";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, id_personne);
            this.ps.setInt(2, id_cours);
            this.ps.setInt(3, annee);

            try (ResultSet rs = this.ps.executeQuery()) {
                return rs.next() ? new Cours_Personne(rs.getInt("id"),
                        rs.getInt("id_personne"),
                        rs.getInt("annee")) : null;
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return null;
    }

    /**
     * Met à jour la table cours_personne avec les nouvelles valeurs pour
     * id_personne et id_cours pour l'année donnée.
     * 
     * @param id_personne l'identifiant de la personne
     * @param id_cours    l'identifiant du cours
     * @param annee       l'année
     * @return le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int update(int id_personne, int id_cours, int annee) throws SQLException {
        String sqlQuery = "UPDATE cours_personne SET id_personne = ?, id_cours = ? WHERE annee = ?";

        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, id_personne);
            this.ps.setInt(2, id_cours);
            this.ps.setInt(3, annee);

            int rowAffected = this.ps.executeUpdate();
            return rowAffected > 0 ? rowAffected : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
    }
}
