package com.mvntest.DAL.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class SectionDAOEmplt implements SectionDAO {

    public SectionDAOEmplt() throws SQLException {
        createSectionTB();
    }

    @Override
    public Section get(int id) throws SQLException {
        String sqlQuery = "SELECT id, nom FROM section WHERE id = ?";

        // using try-ressorces
        try (Connection conn = Database.getConnection();
                PreparedStatement get = conn.prepareStatement(sqlQuery)) {
            
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
            //result = !isExists(t, conn) ? insert(t) : update(t);
        }
        return result;
    }

    @Override
    public int insert(Section t) throws SQLException {
        String sqlQuery = "INSERT INTO section (nom) VALUES (?)";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement insert = conn.prepareStatement(sqlQuery)) {
                    
                    insert.setString(1, t.getNom());
                    int affectedRows = insert.executeUpdate();

                    
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;

    }

    @Override
    public int update(Section s) throws SQLException {
        String sqlQuery = "UPDATE Section set nom=? WHERE id=? ";
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement updateStatement = conn.prepareStatement(sqlQuery)) {
                updateStatement.setString(1, s.getNom());
                updateStatement.setInt(2, s.getId());
                result = updateStatement.executeUpdate();
                System.out.println(result + " row Updated.");
            }
        return result;
    }
    @Override
    public int deleteSection(String nom) throws SQLException{
        String sqlQuery = "DELETE from section WHERE nom = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
                delete.setString(1, nom);
                return delete.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error in deleting data: " + e.getMessage());
        }
        return 0;

    }
    @Override
    public int delete(Section s) throws SQLException {
        String sqlQuery = "DELETE from section WHERE id = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
                delete.setInt(1, s.getId());
                return delete.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error in deleting data: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE from Section WHERE id = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
                    int affectedRows = delete.executeUpdate();
                    if(affectedRows > 0){
                        System.out.println(" Row(s) deleted successfully : " + affectedRows );
                    }
            
        } catch (SQLException e) {
            System.out.println("Error in deleting this data: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int getID(String section) throws SQLException {

        int id = 0;
        String sqlQuery = "SELECT id FROM Status WHERE status = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement idPs = conn.prepareStatement(sqlQuery)) {
            idPs.setString(1, section);
            ResultSet rs = idPs.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        }
        return id;
    }

    @Override
    public void createSectionTB() throws SQLException {
        try (Connection conn = Database.getConnection()) {
            String sqlQuery = "CREATE TABLE IF NOT EXISTS Section ("
                    + "id SERIAL PRIMARY KEY,"
                    + "nom VARCHAR(30)"
                    + ")";
            conn.createStatement().execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
