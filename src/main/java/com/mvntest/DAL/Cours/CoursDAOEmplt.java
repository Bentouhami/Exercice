package com.mvntest.DAL.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class CoursDAOEmplt implements CoursDAO {

    public CoursDAOEmplt() throws SQLException {
        createCoursTB();
    }

    private void createCoursTB() throws SQLException {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS Cours (id SERIAL PRIMARY KEY," +
                " id_section INTEGER, nom VARCHAR(30)," +
                " CONSTRAINT fk_section FOREIGN KEY (id_section) REFERENCES Section(id))";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.execute();
        }
    }

    @Override
    public Cours get(int id) throws SQLException {
        String sqlQuery = "SELECT id, id_section, nom FROM Cours WHERE id = ?";
        try (Connection conn = Database.getConnection();
                PreparedStatement get = conn.prepareStatement(sqlQuery)) {
            if (!isExists(id, conn)) {
                System.out.println("No record exists with this ID");
                return null;
            }
            get.setInt(1, id);
            // Essayer d'exécuter la requête SQL et obtenir le résultat
            try (ResultSet rs = get.executeQuery()) {
                // Si le résultat contient une ligne, créer un nouvel objet Cours avec les
                // données de cette ligne
                // Sinon, retourner null
                return rs.next() ? new Cours(rs.getInt("id"),
                        rs.getInt("id_section"),
                        rs.getString("nom")) : null;
            }
        }
    }

    @Override
    public int getID(String nom) throws SQLException {

        int id = 0;
        String sqlQuery = "SELECT id FROM cours WHERE nom = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement idPs = conn.prepareStatement(sqlQuery)) {
                    idPs.setString(1, nom);
            ResultSet rs = idPs.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }
        return id;
    }

    @Override
    public ArrayList<Cours> getAll() throws SQLException {
        String sqlQuery = "SELECT id, id_section, nom FROM cours";
        ArrayList<Cours> CoursList = new ArrayList<Cours>();
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Cours cour = new Cours(resultSet.getInt("id"),
                            resultSet.getInt("id_section"),
                            resultSet.getString("nom"));
                    CoursList.add(cour);
                }
            }
        }
        return CoursList;
    }

    @Override
    public int save(Cours c) throws SQLException {
        // String sqlQuery = "SELECT COUNT(*) FROM Cours WHERE id = ?";
        int result = 0;
        /*
         * try (// get connection
         * Connection conn = Database.getConnection();
         * PreparedStatement countStmt = conn.prepareStatement(sqlQuery)) {
         * countStmt.setInt(1, c.getId());
         * try (ResultSet resultSet = countStmt.executeQuery()) {
         * if (resultSet.next()) {
         * // get the value retunred and update the count value
         * result = resultSet.getInt(1);
         * }
         * }
         * // Check count value, if count == 0 means no id found than add the object.
         * if (!isExists(c, conn)) {
         * result = insert(c);
         * System.out.println("data inserted succefully");
         * } else {
         * // else means id exists already so update its value.
         * result = update(c);
         * System.out.println("data updated succefully");
         * }
         * }
         */
        try (Connection conn = Database.getConnection()) {
            result = !isExists(c, conn) ? insert(c) : update(c);
        }

        return result;
    }

    @Override
    public int insert(Cours c) throws SQLException {
        String sqlQuery = "INSERT INTO Cours (id_section, nom)" +
                " VALUES ( (SELECT id FROM Secion WHERE nom = ? ),  ? )";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement insert = conn.prepareStatement(sqlQuery)) {
            if (!isExists(c, conn)) {
                System.out.println("No such data");
                return result;
            } else {
                insert.setInt(1, c.getId_section());
                insert.setString(2, c.getNom());
                result = insert.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int update(Cours c) throws SQLException {
        String sqlQuery = "UPDATE cours SET id_section = ?, nom = ? WHERE id  = ?";
        int result = 0;

        // Try to establish a connection and prepare the statement
        try (Connection conn = Database.getConnection();
                PreparedStatement update = conn.prepareStatement(sqlQuery)) {
            // If the course does not exist, print a message and return the result (early
            // stop).
            if (!isExists(c, conn)) {
                System.out.println("No such data");
                return result;
            } else {
                // Set the parameters for the sqlQuery query to update table
                update.setInt(1, c.getId_section());
                update.setString(2, c.getNom());
                update.setInt(3, c.getId());
                // Execute the update query and store the result
                result = update.executeUpdate();
            }
        }
        // Return the result
        return result;
    }

    @Override
    public int delete(Cours c) throws SQLException {
        String sqlQuery = "DELETE FROM cours WHERE id = ? ";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
            if (!isExists(c, conn)) {
                System.out.println("No record found for this ID!");
                return result;
            }
            delete.setInt(1, c.getId());
            result = delete.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in deleting: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM cours WHERE id = ? ";
        int result = 0;
        // Get connection and PreparedStatement.
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
            // Check if id exists in DB.
            if (!isExists(id, conn)) {

                return result;

            } else { // Performing the delete statement
                delete.setInt(1, id);
                result = delete.executeUpdate();

                Database.closePreparedStatement(delete);
                Database.closeConnection(conn);
            }
        }
        return result;
    }

    @Override
    public boolean isExists(int id, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Cours WHERE id = ? ";

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        System.out.println("No record found for this ID!");
        return false;
    }

    @Override
    public boolean isExists(Cours c, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Cours WHERE id = ? ";

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, c.getId());
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
    public boolean isExists(int id) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Cours WHERE id = ? ";

        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                System.out.println("Error excuting the Query: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        System.out.println("No record found for this data!");
        return false;
    }
}
