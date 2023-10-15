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

    public SectionDAOEmplt(Connection conn) throws SQLException {
        this.conn = conn;
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

        // using try-resources
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int sectionId = resultSet.getInt("id");
                    String sectionNom = resultSet.getString("nom");
                    return new Section(sectionId, sectionNom);
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
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
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
        return sections;
    }

    @Override
    public int save(Section section) throws SQLException {
        return section.getId() == 0 ? insert(section) : update(section);

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
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, section.getNom());
            int affectedRows = this.ps.executeUpdate();

            return affectedRows > 0 ? affectedRows : 0;

        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
            return 0;
        }

    }

    @Override
    public int update(Section s) throws SQLException {
        String sqlQuery = "UPDATE Section set nom=? WHERE id=? ";
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
    }

    @Override
    public int deleteSection(String nom) throws SQLException {
        String sqlQuery = "DELETE from section WHERE nom = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, nom);
            int deletedRows = this.ps.executeUpdate();

            return deletedRows > 0 ? deletedRows : 0;

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
    }

    @Override
    public int delete(int id) throws SQLException {
        String sqlQuery = "DELETE from Section WHERE id = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setInt(1, id);
            int affectedRows = this.ps.executeUpdate();
            return affectedRows > 0 ? affectedRows : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
    }

    @Override
    public int getID(String section) throws SQLException {

        int id = 0;
        String sqlQuery = "SELECT id FROM Status WHERE status = ? ";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            this.ps.setString(1, section);
            try (ResultSet rs = this.ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return id;
    }

    @Override
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
        }
    }
}
