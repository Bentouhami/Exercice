package com.mvntest.DAL.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDAOEmplt implements SectionDAO {
    private final String RESULTSET_ERROR = "SQL ResultSet Error: \n";
    private final String PREPAREDSTATEMENT_ERROR = "SQL PreparedStatement Error: \n";
    private final String SQL_ERROR = "SQL Error: \n";
    Connection conn;
    PreparedStatement ps;

<<<<<<< HEAD
    public SectionDAOEmplt(Connection conn) throws SQLException {
        this.conn = conn;
=======
    public SectionDAOEmplt() throws SQLException {
        createSectionTB();
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    }

    /**
     * Obtenir une section par son id.
     * 
     * @param id Type int: l'id de la section à récupérer
     * @return Type Section: la section avec l'id donné, ou null si aucune section
     *         de ce type n'existe
     * @throws SQLException s'il y a une erreur d'accès à la base de données
     */
    @Override
    public Section get(int id) throws SQLException {
        String sqlQuery = "SELECT id, nom FROM section WHERE id = ?";

<<<<<<< HEAD
        // using try-resources
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int sectionId = resultSet.getInt("id");
                    String sectionNom = resultSet.getString("nom");
                    return new Section(sectionId, sectionNom);
=======
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
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
<<<<<<< HEAD
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
=======
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        return null;
    }

    /**
     * Récupérer toutes les sections.
     * 
     * @return Type ArrayList<Section>: une liste de toutes les sections
     * @throws SQLException s'il y a une erreur d'accès à la base de données
     */
    @Override
    public ArrayList<Section> getAll() throws SQLException {
        String sqlQuery = "SELECT id, nom FROM section";
        ArrayList<Section> sections = new ArrayList<Section>();

        // using try-resources
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sections.add(new Section(rs.getInt("id"),
                            rs.getString("nom")));
                }
            } catch (SQLException e) {
<<<<<<< HEAD
                System.out.println(RESULTSET_ERROR + e.getMessage());
=======
                System.out.println(e.getMessage());
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
            }
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
        return sections;
    }

    @Override
<<<<<<< HEAD
    public int save(Section section) throws SQLException {
        return section.getId() == 0 ? insert(section) : update(section);

=======
    public int save(Section t) throws SQLException {
        int result = 0;
        try (Connection conn = Database.getConnection()) {
            //result = !isExists(t, conn) ? insert(t) : update(t);
        }
        return result;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    }

    /**
     * Insérer une section dans la base de données.
     * 
     * @param section Type Section: la section à insérer
     * @return Type int: le nombre de lignes affectées par l'insertion
     * @throws SQLException s'il y a une erreur d'accès à la base de données
     */
    @Override
    public int insert(Section section) throws SQLException {
        String sqlQuery = "INSERT INTO section (nom) VALUES (?)";
<<<<<<< HEAD
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, section.getNom());
            int affectedRows = this.ps.executeUpdate();

            return affectedRows > 0 ? affectedRows : 0;

        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
            return 0;
        }
=======
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement insert = conn.prepareStatement(sqlQuery)) {
                    
                    insert.setString(1, t.getNom());
                    int affectedRows = insert.executeUpdate();

                    
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to the DB: " + e.getMessage());
        }
        return result;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f

    }

    @Override
    public int update(Section s) throws SQLException {
        String sqlQuery = "UPDATE Section set nom=? WHERE id=? ";
<<<<<<< HEAD
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setString(1, s.getNom());
            this.ps.setInt(2, s.getId());

            int rowsAffected = this.ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
=======
        int result = 0;
        try (Connection conn = Database.getConnection();
                PreparedStatement updateStatement = conn.prepareStatement(sqlQuery)) {
                updateStatement.setString(1, s.getNom());
                updateStatement.setInt(2, s.getId());
                result = updateStatement.executeUpdate();
                System.out.println(result + " row Updated.");
            }
        return result;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    }
    @Override
<<<<<<< HEAD
    public int deleteSection(String nom) throws SQLException {
        String sqlQuery = "DELETE from section WHERE nom = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, nom);
            int deletedRows = this.ps.executeUpdate();

            return deletedRows > 0 ? deletedRows : 0;

=======
    public int deleteSection(String nom) throws SQLException{
        String sqlQuery = "DELETE from section WHERE nom = ? ";
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
                delete.setString(1, nom);
                return delete.executeUpdate();
            
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }

    }

    @Override
    public int delete(Section section) throws SQLException {
        String sqlQuery = "DELETE from section WHERE id = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, section.getId());
            int rowAffected = this.ps.executeUpdate();

            return rowAffected > 0 ? rowAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
<<<<<<< HEAD
=======
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
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE from Section WHERE id = ? ";
<<<<<<< HEAD
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setInt(1, id);
            int affectedRows = this.ps.executeUpdate();
            return affectedRows > 0 ? affectedRows : 0;
=======
        try (Connection conn = Database.getConnection();
                PreparedStatement delete = conn.prepareStatement(sqlQuery)) {
                    int affectedRows = delete.executeUpdate();
                    if(affectedRows > 0){
                        System.out.println(" Row(s) deleted successfully : " + affectedRows );
                    }
            
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
<<<<<<< HEAD
=======
        return 0;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    }

    @Override
    public int getID(String section) throws SQLException {

        int id = 0;
        String sqlQuery = "SELECT id FROM Status WHERE status = ? ";
<<<<<<< HEAD
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, section);
            try (ResultSet rs = this.ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
=======
        try (Connection conn = Database.getConnection();
                PreparedStatement idPs = conn.prepareStatement(sqlQuery)) {
            idPs.setString(1, section);
            ResultSet rs = idPs.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return id;
    }

    @Override
<<<<<<< HEAD
    public int insert(String section) throws SQLException {
       String sqlQuery = "INSERT INTO section (nom) VALUES (?)";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, section);
            int affectedRows = this.ps.executeUpdate();

            return affectedRows > 0 ? affectedRows : 0;

        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
            return 0;
=======
    public void createSectionTB() throws SQLException {
        try (Connection conn = Database.getConnection()) {
            String sqlQuery = "CREATE TABLE IF NOT EXISTS Section ("
                    + "id SERIAL PRIMARY KEY,"
                    + "nom VARCHAR(30)"
                    + ")";
            conn.createStatement().execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
        }
    }
}
