package com.mvntest.DAL.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class PersonneDAOEmplt implements PersonneDAO {

    public PersonneDAOEmplt() throws SQLException {
        creatPersonneTB();
    }

    private void creatPersonneTB() throws SQLException {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS Personne ( " +
                "id SERIAL PRIMARY KEY," +
                "id_status INTEGER," +
                "nom VARCHAR(15)," +
                "prenom VARCHAR(15)," +
                "FOREIGN KEY (id_status) REFERENCES Status(id)" +
                ")";

        try (Connection conn = Database.getConnection();
                PreparedStatement st = conn.prepareStatement(sqlQuery)) {
            st.execute();
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create table: " + e.getMessage());
        }
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
        String sqlQuery = "SELECT id, id_status, nom, prenom FROM Personne WHERE id = ?";
        try (Connection conn = Database.getConnection();
                PreparedStatement getPs = conn.prepareStatement(sqlQuery)) {
            if (!isExists(id, conn)) {
                System.out.println("No record exists with this ID");
                return null;
            }
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
            result = !isExists(p, conn) ? insert(p) : update(p);
        } catch (SQLException e) {
            System.out.println("Error saving this data: " + e.getMessage());
        }

        return result;
    }

    /**
     * Insère une nouvelle personne dans la base de données.
     * 
     * @param p La personne à insérer.
     * @return Le nombre de lignes modifiées dans la base de données.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public int insert(Personne p) throws SQLException {

        String sqlQuery = "INSERT INTO Personne (id_status, nom, prenom)" +
                "VALUES (?, ?, ?)";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement insertSp = conn.prepareStatement(sqlQuery)) {
            if (isExists(p, conn)) {
                return result;
            } else {
                insertSp.setInt(1, p.getId_status());
                insertSp.setString(2, p.getNom());
                insertSp.setString(3, p.getPrenom());
                insertSp.executeUpdate();
                System.out.println("row inserted.");
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;
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
            if (!isExists(p, conn)) {
                return result;
            } else {
                updatePs.setInt(1, p.getId_status());
                updatePs.setString(2, p.getNom());
                updatePs.setString(3, p.getPrenom());
                updatePs.setInt(4, p.getId());
                updatePs.executeUpdate();
                System.out.println("Updated.");
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while trying to execute the PreparedStatement: " + e.getMessage());
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
        try (Connection conn = Database.getConnection();
                PreparedStatement deletePs = conn.prepareStatement(sqlQuery)) {

            if (!isExists(p, conn)) {
                return result;
            } else {
                deletePs.setInt(1, p.getId());
                result = deletePs.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to execute the PreparedStatement: " + e.getMessage());
        }
        return result;
    }

    /**
     * Supprime une personne de la base de données en utilisant son ID.
     * 
     * @param id L'ID de la personne à supprimer.
     * @return Le nombre de lignes supprimées.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public int delete(int id) throws SQLException {
        int result = 0;
        String sqlQuery = "DELETE FROM Personne WHERE id = ?";
        try (Connection conn = Database.getConnection();
                PreparedStatement deletePs = conn.prepareStatement(sqlQuery)) {
            if (!isExists(id, conn)) {
                return result;
            } else {
                deletePs.setInt(1, id);
                result = deletePs.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to execute the PreparedStatement: " + e.getMessage());
        }
        return result;
    }

    /**
     * Vérifie si une personne est inscrite à un cours en utilisant son ID.
     * 
     * @param id   L'ID de la personne à vérifier.
     * @param conn La connexion à la base de données.
     * @return true si la personne est inscrite, false sinon.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public boolean isExists(int id, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Personne_Cours WHERE id = ? ";
        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                System.out.println("Error while trying to get data from the DB: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to execute the PreparedStatement: " + e.getMessage());
        }
        return false;
    }

    /**
     * Vérifie si une personne existe dans la base de données en utilisant son ID.
     * 
     * @param p    La personne à vérifier.
     * @param conn La connexion à la base de données.
     * @return true si la personne existe, false sinon.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public boolean isExists(Personne p, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Personne WHERE id = ? ";
        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, p.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                System.out.println("Error while trying to get data from the DB: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to execute the PreparedStatement: " + e.getMessage());
        }
        return false;
    }

    /**
     * Cette méthode vérifie si une personne existe dans la base de données en
     * utilisant son ID.
     * 
     * @param id L'ID de la personne à vérifier.
     * @return true si la personne existe, false sinon.
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de
     *                      données.
     */
    @Override
    public boolean isExists(int id) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Personne WHERE id = ? ";

        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                System.out.println("Error while trying to get data from the DB: " + e.getMessage());
            }
        }
        System.out.println("Aucun enregistrement trouvé pour ces données!");
        return false;
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
    public int getID(String nom) throws SQLException {

        int id = 0;
        String sqlQuery = "SELECT id FROM Personne WHERE nom = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement idPs = conn.prepareStatement(sqlQuery)) {
            ResultSet rs = idPs.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }
        return id;
    }

}
