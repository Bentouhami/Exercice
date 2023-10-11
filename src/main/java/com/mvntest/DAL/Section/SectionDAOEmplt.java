package com.mvntest.DAL.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class SectionDAOEmplt implements SectionDAO {

    
    public SectionDAOEmplt() {
        String sqlQuery =   "CREATE TABLE IF NOT EXISTS Section ("+
                                "id SERIAL PRIMARY KEY,"+ 
                                "nom VARCHAR(30)"+
                            ")";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Section get(int id) throws SQLException {
        String sqlQuery = "SELECT id, nom from section where id = ?";

        // using try-ressorces
        try (Connection conn = Database.getConnection();
                PreparedStatement get = conn.prepareStatement(sqlQuery)) {
            if (!isExists(id, conn)) {
                System.out.println("section not found");
                return null;
            } else {
                get.setInt(1, id);
                try (ResultSet resultSet = get.executeQuery()) {
                    // i used 'if' in this place because i'm expecting only one result
                    if (resultSet.next()) {
                        int sectionId = resultSet.getInt("id");
                        String sectionNom = resultSet.getString("nom");
                        return new Section(sectionId, sectionNom);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Section> getAll() throws SQLException {
        String sqlQuery = "SELECT id, nom FROM section";
        ArrayList<Section> sections = new ArrayList<Section>();

        // try-ressorces
        try (Connection connection = Database.getConnection();
                PreparedStatement getAll = connection.prepareStatement(sqlQuery)) {
            try (ResultSet resultSet = getAll.executeQuery()) {
                while (resultSet.next()) {
                    Section section = new Section(resultSet.getInt("id"),
                            resultSet.getString("nom"));
                    sections.add(section);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sections;
    }

    @Override
    public int save(Section t) throws SQLException {
        int result = 0;
        try (Connection conn = Database.getConnection()) {
                result = !isExists(t, conn) ? insert(t) : update(t);
            return result;
        }
    }

    @Override
    public int insert(Section t) throws SQLException {
        String sqlQuery = "INSERT INTO section (nom) VALUES (?)";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement insert = conn.prepareStatement(sqlQuery)) {
                    if(!isExists(t, conn)){
                        return result;
                    } else {
                        insert.setString(1, t.getNom());
                        result = insert.executeUpdate();
                        System.out.println(" row inserted.");
                    }
            return result;
        }
    }

    @Override
    public int update(Section s) throws SQLException {
        String sqlQuery = "UPDATE Section set nom=? WHERE id=? ";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement updateStatement = conn.prepareStatement(sqlQuery)) {

            if (!isExists(result, conn)){
                return result;
            } else {
                updateStatement.setString(1, s.getNom());
                updateStatement.setInt(2, s.getId());
                result = updateStatement.executeUpdate();
                System.out.println("Updated.");
            }
        }
        return result;
    }

    @Override
    public int delete(Section s) throws SQLException {
        String sqlQuery = "DELETE from section WHERE id = ? ";
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
            System.out.println("Error in deleting data: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE from Section WHERE id = ? ";
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
            System.out.println("Error in deleting this data: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean isExists(int id, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Section WHERE id = ? ";
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
    public boolean isExists(Section s, Connection conn) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) FROM Section WHERE id = ? ";
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
        String sqlQuery = "SELECT COUNT(*) FROM Section WHERE id = ? ";

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

}
