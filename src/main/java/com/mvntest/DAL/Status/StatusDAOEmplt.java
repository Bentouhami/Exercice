package com.mvntest.DAL.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class StatusDAOEmplt implements StatusDAO {

    public StatusDAOEmplt() throws SQLException {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS Status ( id SERIAL PRIMARY KEY, status VARCHAR(30))";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.execute();
        }

    }

    @Override
    public Status get(int id) throws SQLException {
        String sqlQuery = "SELECT id, status from status where id = ?";
        // using try-ressorces
        try (Connection conn = Database.getConnection();
                PreparedStatement get = conn.prepareStatement(sqlQuery)) {
            if (!isExists(id, conn)) {
                System.out.println("status not found");
                return null;
            } else {
                get.setInt(1, id);
                try (ResultSet resultSet = get.executeQuery()) {
                    // i used 'if' in this place because i'm expecting only one result
                    if (resultSet.next()) {
                        int statusId = resultSet.getInt("id");
                        String status = resultSet.getString("status");
                        return new Status(statusId, status);
                    }
                }
            }
        }
        return null;

    }

    @Override
    public ArrayList<Status> getAll() throws SQLException {

        String sqlQuery = "SELECT id, status FROM status";
        ArrayList<Status> statusList = new ArrayList<Status>();

        // try-ressorces
        try (Connection connection = Database.getConnection();
                PreparedStatement getAll = connection.prepareStatement(sqlQuery)) {
            try (ResultSet resultSet = getAll.executeQuery()) {
                while (resultSet.next()) {
                    Status statu = new Status(resultSet.getInt("id"),
                            resultSet.getString("status"));
                    statusList.add(statu);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return statusList;
    }

    @Override
    public int save(Status s) throws SQLException {

        int result = 0;
        try (Connection conn = Database.getConnection()) {
            result = !isExists(s, conn) ? insert(s) : update(s);
        }
        return result;
    }

    @Override
    public int insert(Status s) throws SQLException {

        String sqlQuery = "INSERT INTO status (status) VALUES (?)";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement insert = conn.prepareStatement(sqlQuery)) {
            if (isExists(s, conn)) {
                return result;
            } else {
                insert.setString(1, s.getStatus());
                insert.execute();
                System.out.println(" row inserted.");
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int update(Status s) throws SQLException {

        String sqlQuery = "UPDATE Status SET status = ? WHERE id=? ";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement updateStatement = conn.prepareStatement(sqlQuery)) {
            if (!isExists(s, conn)) {
                return result;
            } else {
                updateStatement.setString(1, s.getStatus());
                updateStatement.setInt(2, s.getId());
                result = updateStatement.executeUpdate();
                System.out.println("Updated.");
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;

    }

    @Override
    public int delete(Status s) throws SQLException {

        String sqlQuery = "DELETE from status WHERE id = ? ";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
            if (!isExists(s, conn)) {
                return result;
            } else {
                delete.setInt(1, s.getId());
                result = delete.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {

        String sqlQuery = "DELETE FROM Status WHERE id = ?";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
            if (!isExists(id, conn)) {
                return result;
            } else {
                delete.setInt(1, id);
                result = delete.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }

        return result;
    }

    @Override
    public boolean isExists(int id, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Status WHERE id = ? ";
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
    public boolean isExists(Status s, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM status WHERE id = ? ";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setInt(1, s.getId());
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        System.out.println("No record found for this data!");
        return false;
    }

    @Override
    public boolean isExists(int id) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Status WHERE id = ? ";

        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
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
    public int getID(String status) throws SQLException {

        int id = 0;
        String sqlQuery = "SELECT id FROM Status WHERE status = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement idPs = conn.prepareStatement(sqlQuery)) {
            idPs.setString(1, status);
            ResultSet rs = idPs.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        }
        return id;
    }

}
