package com.mvntest.DAL.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DAL.Status.Status;
import com.mvntest.DAL.Status.StatusDAO;
import com.mvntest.DAL.Status.StatusDAOEmplt;
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
                // Si le résultat contient une ligne, créer un nouvel objet Cours avec les
                // données de cette ligne
                // Sinon, retourner null
                return rs.next() ? new Personne(rs.getInt("id"),
                        rs.getInt("id_status"),
                        rs.getString("nom"),
                        rs.getString("prenom")) : null;
            }
        }
    }

    @Override
    public ArrayList<Personne> getAll() {
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

    @Override
    public int save(Personne p) {
        int result = 0;
        try (Connection conn = Database.getConnection()) {
            result = !isExists(p, conn) ? insert(p) : update(p);
        } catch (SQLException e) {
            System.out.println("Error saving this data: " + e.getMessage());
        }

        return result;
    }

    @Override
    public int insert(Personne p) {

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
                System.out.println("Updated.");
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }

        return result;
    }

    @Override
    public int delete(Personne p) {
        
        int result = 0;
        String sqlQuery = "DELETE FROM Personne WHERE id = ?";
        try (Connection conn = Database.getConnection();
        PreparedStatement deletePs = conn.prepareStatement(sqlQuery)) {

            if(!isExists(p, conn)){
                return result;
            } else {
                deletePs.setInt(1, p.getId());
                result = deletePs.executeUpdate();
            }            
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;
    }
    @Override
    public int delete(int id) {
        
        int result = 0;
        String sqlQuery = "DELETE FROM Personne WHERE id = ?";
        try (Connection conn = Database.getConnection();
        PreparedStatement deletePs = conn.prepareStatement(sqlQuery)) {

            if(!isExists(id, conn)){
                return result;
            } else {
                deletePs.setInt(1, id);
                result = deletePs.executeUpdate();
            }            
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean isExists(int id, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Personne_Cours WHERE id = ? ";
        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isExists(Personne p, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Personne WHERE id = ? ";
        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, p.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

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
            }
        }
        System.out.println("No record found for this data!");
        return false;
    }

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
