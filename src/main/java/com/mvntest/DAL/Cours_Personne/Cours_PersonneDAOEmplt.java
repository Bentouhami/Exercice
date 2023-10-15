package com.mvntest.DAL.Cours_Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class Cours_PersonneDAOEmplt implements Cours_PersonneDAO {

    /**
     * Constructeur de la classe Cours_PersonneDAOEmplt.
     * 
     * @throws SQLException si une erreur SQL survient
     */
    public Cours_PersonneDAOEmplt() throws SQLException {
        createCoursPersonneTB();
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
        String sqlQuery = "select id_personne, id_cours, annee FROM cours_personne WHERE  annee = ?";
        try {
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
                ps.setInt(1, annee);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next() ? new Cours_Personne(rs.getInt("id_personne"), rs.getInt("id_cours"),
                            rs.getInt("annee")) : null;
                }
            } catch (SQLException e) {
                System.out.println("SQL PreparedStatement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
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
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
                ps.setInt(1, annee);
                int rowAffected = ps.executeUpdate();
                return rowAffected > 0 ? rowAffected : 0;

            } catch (SQLException e) {
                System.out.println("SQL PreparedStatement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
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
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery);
                    ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Cours_Personne cours_personne = new Cours_Personne(rs.getInt("id_personne"),
                            rs.getInt("id_cours"),
                            rs.getInt("annee"));
                    coursPersonneListe.add(cours_personne);
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
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

        // If the year of the course is 0, insert the course_personne object into the
        // database.
        // Otherwise, update the course_personne object in the database.
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
            Connection connection = Database.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, nom);
                statement.setString(2, prenom);
                statement.setString(3, coursNom);
                statement.setInt(4, annee);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("No rows affected, insertion failed!");
                } else {
                    System.out.println("Insertion succeeded, " + affectedRows + " row(s) affected.");
                }
                return affectedRows;

            } catch (SQLException e) {
                System.out.println("SQL Error Prepared Statement: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
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
            Connection connection = Database.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, c_p.getId_personne());
                statement.setInt(2, c_p.getId_cours());
                statement.setInt(3, c_p.getAnnee());

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("No rows affected, insertion failed!");
                } else {
                    System.out.println("Insertion succeeded, " + affectedRows + " row(s) affected.");
                }
                return affectedRows;
            } catch (SQLException e) {
                System.out.println("SQL Prepared Statement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
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
    public int insert(int id_section, int id_cours, int annee) throws SQLException {
        String sqlQuery = "INSERT INTO cours_personne( id_personne, id_cours, annee )"
                + " VALUES( ?,?,? )";

        try {
            Connection connection = Database.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id_section);
                statement.setInt(2, id_cours);
                statement.setInt(3, annee);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("No rows affected, insertion failed!");
                } else {
                    System.out.println("Insertion succeeded, " + affectedRows + " row(s) affected.");
                }
                return affectedRows;
            } catch (SQLException e) {
                System.out.println("SQL Prepared Statement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
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
     * Crée la table Cours_Personne dans la base de données.
     * 
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public void createCoursPersonneTB() throws SQLException {
        try (Connection conn = Database.getConnection()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Cours_Personne ("
                    + " id_personne INTEGER NOT NULL,"
                    + " id_cours INTEGER NOT NULL,"
                    + " annee INTEGER NOT NULL,"
                    + " PRIMARY KEY (id_personne, id_cours, annee),"
                    + " FOREIGN KEY (id_personne) REFERENCES Personne(id),"
                    + " FOREIGN KEY (id_cours) REFERENCES Cours(id)"
                    + " )";
            conn.createStatement().execute(createTableSQL);
            } catch (SQLException e) {
                System.err.println("Failed to create table Cours_Personne: " + e.getMessage());
            }
    }

    /**
     * Récupère un objet Cours_Personne dans la base de données.
     * 
     * @param id_personne Type int: l'identifiant de la personne
     * @param id_cours Type int: l'identifiant du cours
     * @param annee Type int: l'année
     * @return Type Cours_Personne: l'objet Cours_Personne correspondant aux paramètres donnés
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public Cours_Personne get(int id_personne, int id_cours, int annee) throws SQLException {
        String sqlQuery = "SELECT id_personne, id_cours, annee FROM cours_personne WHERE id_personne = ? AND id_cours = ? AND annee =  ?";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, id_personne);
            ps.setInt(2, id_cours);
            ps.setInt(3, annee);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cours_Personne(rs.getInt("id"), rs.getInt("id_personne"), rs.getInt("annee"));
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
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
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
                ps.setInt(1, id_personne);
                ps.setInt(2, id_cours);
                ps.setInt(3, annee);

                int rowAffected = ps.executeUpdate();
                return rowAffected > 0 ? rowAffected : 0;

            } catch (SQLException e) {
                System.out.println("SQL PreparedStatement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return 0;
    }
}
