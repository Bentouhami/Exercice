package com.mvntest.DAL.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDAOEmplt implements StatusDAO {

    private final String RESULTSET_ERROR = "SQL ResultSet Error: \n";
    private final String PREPAREDSTATEMENT_ERROR = "SQL PreparedStatement Error: \n";
    private final String SQL_ERROR = "SQL Error: \n";
    Connection conn;
    PreparedStatement ps;

    public StatusDAOEmplt(Connection conn) throws SQLException {

        this.conn = conn;

    }

    @Override
    public Status get(int id) throws SQLException {
        String sqlQuery = "SELECT id, status from status where id = ?";
        // using try-ressorces
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                // i used 'if' in this place because i'm expecting only one result
                if (resultSet.next()) {
                    int statusId = resultSet.getInt("id");
                    String status = resultSet.getString("status");
                    return new Status(statusId, status);
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
        return null;

    }

    @Override
    public ArrayList<Status> getAll() throws SQLException {

        String sqlQuery = "SELECT id, status FROM status";
        ArrayList<Status> statusList = new ArrayList<Status>();

        // try-ressorces
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    statusList.add( new Status(resultSet.getInt("id"),
                            resultSet.getString("status")));
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
        return statusList;
    }

    @Override
    public int save(Status s) throws SQLException {

        return s.getId() == 0 ? insert(s) : update(s);
    }

    @Override
    public int insert(Status s) throws SQLException {

        String sqlQuery = "INSERT INTO status (status) VALUES (?)";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            ps.setString(1, s.getStatus());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Status s) throws SQLException {

        String sqlQuery = "UPDATE Status SET status = ? WHERE id=? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, s.getStatus());
            this.ps.setInt(2, s.getId());

            int rowsAffected = this.ps.executeUpdate();

            return rowsAffected > 0 ? rowsAffected : 0;

        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
            return 0;
        }

    }

    @Override
    public int delete(Status s) throws SQLException {

        String sqlQuery = "DELETE from status WHERE id = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setInt(1, s.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(String status) {
        String sqlQuery = "DELETE from status WHERE status = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            ps.setString(1, status);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;

        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM Status WHERE id = ?";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            int rowAffected = ps.executeUpdate();
            return rowAffected > 0 ? rowAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    @Override
    public int getID(String status) throws SQLException {
        String sqlQuery = "SELECT id FROM Status WHERE status = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    System.out.println("No ID Found for given status :" + status);
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
    }

    @Override
    public int insert(String status) throws SQLException {
        String sqlQuery = "INSERT INTO status (status) VALUES (?)";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            ps.setString(1, status);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
    }

}
