package com.mvntest.DAL.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class PersonneDAOEmplt implements PersonneDAO {

    public PersonneDAOEmplt() throws SQLException {
        createPersonneTB();
    }

    /**
     * Récupère une personne de la base de données en utilisant son ID.
     * 
     * @param id L'ID de la personne à récupérer.
     * @return La personne récupérée, ou null si aucune personne n'existe avec cet
     *         ID.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public Personne get(int id) throws SQLException {
        String sqlQuery = "SELECT id_status, nom, prenom FROM Personne WHERE id = ?";
        try (Connection conn = Database.getConnection();
                PreparedStatement getPs = conn.prepareStatement(sqlQuery)) {
            /*
             * if (!isExists(id, conn)) {
             * System.out.println("No record exists with this ID");
             * return null;
             * }
             */
            getPs.setInt(1, id);
            // Essayer d'exécuter la requête SQL et obtenir le résultat
            try (ResultSet rs = getPs.executeQuery()) {
                // Si le résultat contient une ligne, créer un nouvel objet Personne avec les
                // données de cette ligne
                return rs.next() ? new Personne(rs.getInt("id"),
                        rs.getInt("id_status"),
                        rs.getString("nom"),
                        rs.getString("prenom")) : null;
            } catch (SQLException e) {
                System.out.println("Error trying to get connection the DB : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get connection the DB : " + e.getMessage());
        }
        return null;
    }

    /**
     * Récupère toutes les personnes de la base de données.
     * 
     * @return Une liste de toutes les personnes de la base de données.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public ArrayList<Personne> getAll() throws SQLException {
        String sqlQury = "SELECT id, id_status, nom, prenom FROM Personne";
        ArrayList<Personne> listOfPersons = new ArrayList<Personne>();
        try (Connection conn = Database.getConnection();
                PreparedStatement getAllPs = conn.prepareStatement(sqlQury)) {
            try (ResultSet rs = getAllPs.executeQuery()) {
                while (rs.next()) {
                    Personne personne = new Personne(rs.getInt("id"),
                            rs.getInt("id_status"),
                            rs.getString("nom"),
                            rs.getString("prenom"));
                    listOfPersons.add(personne);
                }
            } catch (SQLException e) {
                System.out.println("Error trying to excute the SQL : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error trying to get connection the DB : " + e.getMessage());
        }

        return listOfPersons;
    }

    /**
     * Enregistre une personne dans la base de données. Si la personne existe déjà,
     * met à jour ses données.
     * 
     * @param p La personne à enregistrer ou mettre à jour.
     * @return Le nombre de lignes modifiées dans la base de données.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public int save(Personne p) throws SQLException {
        int result = 0;
        try (Connection conn = Database.getConnection()) {
            // result = !isExists(p, conn) ? insert(p) : update(p);
        } catch (SQLException e) {
            System.out.println("Error saving this data: " + e.getMessage());
        }

        return result;
    }

    
    @Override
    public int insert(Personne person) throws SQLException {

        String sqlQuery = "INSERT INTO Personne (id_status, nom, prenom)" +
                "VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, person.getId_status());
            ps.setString(2, person.getNom());
            ps.setString(3, person.getPrenom());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) inserted.");
                return rowsAffected;
            } else {
                System.out.println(rowsAffected + " row(s) inserted.");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return 0;

    }

    /**
     * Met à jour une personne dans la base de données.
     * 
     * @param p La personne à mettre à jour.
     * @return Le nombre de lignes mises à jour.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public int update(Personne p) {
        String sqlQuery = "UPDATE Personne SET " +
                "id_status = ?," +
                "nom = ?," +
                "prenom = ?" +
                "WHERE id = ?";
        int result = 0;
        ;
        try (Connection conn = Database.getConnection();
                PreparedStatement updatePs = conn.prepareStatement(sqlQuery)) {
            /*
             * if (!isExists(p, conn)) {
             * return result;
             * } else {
             * updatePs.setInt(1, p.getId_status());
             * updatePs.setString(2, p.getNom());
             * updatePs.setString(3, p.getPrenom());
             * updatePs.setInt(4, p.getId());
             * updatePs.executeUpdate();
             * System.out.println("Updated.");
             * }
             */
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return result;
    }

    /**
     * Supprime une personne de la base de données en utilisant son ID.
     * 
     * @param p La personne à supprimer.
     * @return Le nombre de lignes supprimées.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public int delete(Personne p) {

        int result = 0;
        String sqlQuery = "DELETE FROM Personne WHERE id = ?";

        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM Personne WHERE id = ?";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery);) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " Personne deleted from Database.");
                return rowsAffected;
            } else {
                System.out.println(rowsAffected + " row(s) deleted.");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return 0;
    }

    /**
     * Cette méthode est utilisée pour obtenir l'ID d'une personne en utilisant son
     * nom.
     * 
     * @param nom Le nom de la personne dont l'ID est requis.
     * @return L'ID de la personne. Si aucune personne avec ce nom n'est trouvée,
     *         retourne 0.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public int getID(String nom, String prenom) throws SQLException {
        int id = 0;
        String sqlQuery = "SELECT id FROM Personne WHERE nom = ? AND prenom = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement idPs = conn.prepareStatement(sqlQuery)) {
            idPs.setString(1, nom);
            idPs.setString(2, prenom);
            try (ResultSet rs = idPs.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        }
        return id;

    }

    @Override
    public void createPersonneTB() throws SQLException {
        try (Connection conn = Database.getConnection()) {
            String sqlQuery = "CREATE TABLE IF NOT EXISTS Personne ("
                    + "id SERIAL PRIMARY KEY NOT NULL,"
                    + "id_status INTEGER NOT NULL,"
                    + "nom VARCHAR(30),"
                    + "prenom VARCHAR(30),"
                    + "FOREIGN KEY (id_status) REFERENCES Status(id)"
                    + ")";

            conn.createStatement().execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

}
