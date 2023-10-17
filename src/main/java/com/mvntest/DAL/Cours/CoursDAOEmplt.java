package com.mvntest.DAL.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

<<<<<<< HEAD
=======
import com.mvntest.DBConfig.Database;

>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
/**
 * Cette classe représente un objet d'accès aux données pour les cours.
 */
public class CoursDAOEmplt implements CoursDAO {
    private final String RESULTSET_ERROR = "SQL ResultSet Error: \n";
    private final String PREPAREDSTATEMENT_ERROR = "SQL PreparedStatement Error: \n";
    private final String SQL_ERROR = "SQL Error: \n";
    private Connection conn;
    private PreparedStatement ps;

<<<<<<< HEAD
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public CoursDAOEmplt(Connection conn) throws SQLException {

        this.conn = conn;
    }

=======
    public CoursDAOEmplt() throws SQLException {
        createCoursTable();
    }

    /**
     * Crée la table Cours dans la base de données.
     * 
     * @throws SQLException si une erreur SQL survient
     */
    private void createCoursTable() throws SQLException {
        try (Connection conn = Database.getConnection()) {
            String sqlQuery = "CREATE TABLE IF NOT EXISTS Cours ("
                    + " id SERIAL PRIMARY KEY,"
                    + " id_section INTEGER,"
                    + " nom VARCHAR(30),"
                    + " CONSTRAINT fk_section FOREIGN KEY (id_section) REFERENCES Section(id))";

            conn.createStatement().execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("Error trying to create table: " + e.getMessage());
        }
    }

    
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    /**
     * Récupère un cours à partir de son id.
     * 
     * @param id Type int: l'id du cours à récupérer
     * @return Type Cours: le cours correspondant, ou null s'il n'existe pas
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public Cours get(int id) throws SQLException {
        String sqlQuery = "SELECT id, id_section, nom FROM Cours WHERE id = ?";
        try {
<<<<<<< HEAD
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, id);
            try (ResultSet rs = this.ps.executeQuery()) {
                return rs.next() ? new Cours(rs.getInt("id"),
                        rs.getInt("id_section"),
                        rs.getString("nom")) : null;
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
=======
            Connection conn = Database.getConnection();
            try (PreparedStatement get = conn.prepareStatement(sqlQuery)) {
                
                get.setInt(1, id);
                // Try to execute the SQL query and get the result
                try (ResultSet rs = get.executeQuery()) {
                    // If the result contains a row, create a new Cours object with the data from that row
                    // Otherwise, return null
                    return rs.next() ? new Cours(rs.getInt("id"),
                    rs.getInt("id_section"),
                    rs.getString("nom")) : null;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: = " + e.getMessage());
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        }
        return null;
    }

    /**
     * Récupère l'ID d'un cours à partir de son id_section et de son nom.
     * 
     * @param id_section Type int: l'id de la section du cours
<<<<<<< HEAD
     * @param nom        Type String: le nom du cours
=======
     * @param nom Type String: le nom du cours
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
     * @return Type int: l'id du cours correspondant, ou 0 s'il n'existe pas
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int getID(int id_section, String nom) throws SQLException {

<<<<<<< HEAD
        String sqlQuery = "SELECT id FROM cours WHERE id_section= ? AND nom = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, id_section);
            this.ps.setString(2, nom);
            try (ResultSet rs = this.ps.executeQuery()) {

                return rs.next() ? rs.getInt(1) : 0;
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
=======
        int id = 0;
        String sqlQuery = "SELECT id FROM cours WHERE id_section= ? AND nom = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement idPs = conn.prepareStatement(sqlQuery)) {
            idPs.setInt(1, id_section);
            idPs.setString(2, nom);
            try (ResultSet rs = idPs.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

<<<<<<< HEAD
    /**
     * Récupère tous les cours de la base de données.
     * 
     * @return Type ArrayList<Cours>: la liste de tous les cours de la base de
     *         données
=======
    
    /**
     * Récupère tous les cours de la base de données.
     * 
     * @return Type ArrayList<Cours>: la liste de tous les cours de la base de données
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public ArrayList<Cours> getAll() throws SQLException {
        String sqlQuery = "SELECT id, id_section, nom FROM cours";
        ArrayList<Cours> CoursList = new ArrayList<Cours>();
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            try (ResultSet resultSet = this.ps.executeQuery()) {
                while (resultSet.next()) {
                    CoursList.add(new Cours(resultSet.getInt("id"),
                            resultSet.getInt("id_section"),
                            resultSet.getString("nom")));
                }
            } catch (SQLException e) {
<<<<<<< HEAD
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
=======
                System.out.println("SQL Error ResultSet : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        }
        return CoursList;
    }

    /**
<<<<<<< HEAD
     * Enregistre un cours dans la base de données. Si le cours n'existe pas, il est
     * inséré. Sinon, il est mis à jour.
=======
     * Enregistre un cours dans la base de données. Si le cours n'existe pas, il est inséré. Sinon, il est mis à jour.
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
     * 
     * @param c Type Cours: le cours à enregistrer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int save(Cours c) throws SQLException {

        return c.getId() == 0 ? insert(c) : update(c);
<<<<<<< HEAD
    }

    /**
     * Met à jour un cours dans la base de données.
     * 
     * @param c Type Cours: le cours à mettre à jour
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int update(Cours c) throws SQLException {
        String sqlQuery = "UPDATE cours SET id_section = ?, nom = ? WHERE id  = ?";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, c.getId_section());
            this.ps.setString(2, c.getNom());
            this.ps.setInt(3, c.getId());

            int rowsAffected = this.ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    /**
     * Supprime un cours de la base de données.
     * 
     * @param c Type Cours: le cours à supprimer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int delete(Cours c) throws SQLException {
        String sqlQuery = "DELETE FROM cours WHERE id = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, c.getId());
            int rowsAffected = this.ps.executeUpdate();

            return rowsAffected > 0 ? rowsAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }

        return 0;
    }

    /**
     * Supprime un cours de la base de données.
     * 
     * @param id Type int: l'id du cours à supprimer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM cours WHERE id = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setInt(1, id);
            int rowAffected = this.ps.executeUpdate();

            return rowAffected > 0 ? rowAffected : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
=======
    }
    /**
     * Met à jour un cours dans la base de données.
     * 
     * @param c Type Cours: le cours à mettre à jour
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int update(Cours c) throws SQLException {
        String sqlQuery = "UPDATE cours SET id_section = ?, nom = ? WHERE id  = ?";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, c.getId_section());
            ps.setString(2, c.getNom());
            ps.setInt(3, c.getId());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                System.err.println(rowsAffected + " updated.");
                return rowsAffected;
            } else {
                System.out.println(rowsAffected + " updated.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Supprime un cours de la base de données.
     * 
     * @param c Type Cours: le cours à supprimer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int delete(Cours c) throws SQLException {
        String sqlQuery = "DELETE FROM cours WHERE id = ? ";
        try {
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
                ps.setInt(1, c.getId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Cours deleted successfully.");
                    return rowsAffected;
                }

            } catch (SQLException e) {
                System.out.println("SQL PrepaparedStatement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return 0;
    }

    /**
     * Supprime un cours de la base de données.
     * 
     * @param id Type int: l'id du cours à supprimer
     * @return Type int: le nombre de lignes affectées par l'opération
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM cours WHERE id = ? ";
        try {
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
                ps.setInt(1, id);
                int rowAffected = ps.executeUpdate();
                if (rowAffected > 0) {
                    // System.out.println("Cours deleted succesfully.");
                    return rowAffected;
                }
            } catch (SQLException e) {
                System.out.println("SQL PreparedStatement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Insère un cours dans la base de données.
     * 
     * @param c Type Cours: le cours à insérer
     * @return Type int: le nombre de lignes affectées par l'insertion
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int insert(Cours c) throws SQLException {
        String sqlQuery = "INSERT INTO cours (id_section , nom)"
                + "VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, c.getId_section());
            ps.setString(2, c.getNom());
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Cours inserted successfully");
                return rowAffected;
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Insère un cours dans la base de données.
     * 
     * @param id_section Type int: l'id de la section du cours
     * @param coursNom   Type String : le nom du cours
     * @return Type int: le nombre de lignes affectées par l'insertion
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int insert(int id_section, String coursNom) throws SQLException {

        String sqlQuery = "INSERT INTO Cours (id_section, nom)"
                + "VALUES("
                + "((SELECT id FROM Section WHERE nom = ? ),"
                + "?)"
                + ")";
        try {
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

                ps.setInt(1, id_section);
                ps.setString(2, coursNom);

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Creating cours failed, no rows affected.");
                    return affectedRows;
                } else {
                    System.out.println("Cours created successfully");
                }
            } catch (SQLException e) {
                System.out.println("SQL PreparedStatement Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        }
        return 0;
    }

    /**
<<<<<<< HEAD
     * Insère un cours dans la base de données.
     * 
     * @param c Type Cours: le cours à insérer
     * @return Type int: le nombre de lignes affectées par l'insertion
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int insert(Cours c) throws SQLException {
        String sqlQuery = "INSERT INTO cours (id_section , nom)"
                + "VALUES (?, ?)";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, c.getId_section());
            this.ps.setString(2, c.getNom());
            int rowAffected = this.ps.executeUpdate();

            return rowAffected > 0 ? rowAffected : 0;
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
        return 0;
    }

    /**
     * Insère un cours dans la base de données.
     * 
     * @param id_section Type int: l'id de la section du cours
     * @param coursNom   Type String : le nom du cours
     * @return Type int: le nombre de lignes affectées par l'insertion
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int insert(String sectionName, String coursNom) throws SQLException {
        /*
         * String sqlQuery = "INSERT INTO Cours (id_section, nom)"
         * + "VALUES("
         * + "((SELECT id FROM Section WHERE nom = ? )"
         * + "?)"
         */;

        String sqlQuery = "INSERT INTO Cours (id_section, nom) VALUES ((SELECT id FROM Section WHERE nom = ?), ?)";

        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, sectionName);
            this.ps.setString(2, coursNom);

            int rowAffected = this.ps.executeUpdate();
            return rowAffected > 0 ? rowAffected : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    /**
=======
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
     * Récupère l'ID d'un cours à partir de son nom.
     * 
     * @param s String, le nom du cours
     * @return Type de retour : int, l'ID du cours ou 0 si aucun cours n'a été
     *         trouvé
     * @throws SQLException si une erreur SQL survient
     */
    @Override
    public int getID(String s) throws SQLException {
        String sqlQuery = "SELECT id FROM cours WHERE nom = ?";
        try {
<<<<<<< HEAD
            this.ps = this.conn.prepareStatement(sqlQuery);

            ps.setString(1, s);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }

        } catch (SQLException e) {

            System.out.println(SQL_ERROR + e.getMessage());
=======
            Connection conn = Database.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

                ps.setString(1, s);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("ID du cours trouvé !");
                        return rs.getInt(1);
                    } else {
                        System.out.println("Aucun ID de cours trouvé pour ce nom : " + s + "\n");
                    }
                } catch (SQLException e) {

                    System.out.println("Erreur SQL ResultSet : " + e.getMessage());
                }

            } catch (SQLException e) {

                System.out.println("Erreur SQL PreparedStatement : " + e.getMessage());
            }
        } catch (SQLException e) {

            System.out.println("Erreur SQL : " + e.getMessage());
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        }
        return 0;
    }

}
