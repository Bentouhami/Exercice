package com.mvntest.DAL.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class PersonneDAOEmplt implements PersonneDAO {

    private final String RESULTSET_ERROR = "SQL ResultSet Error: \n";
    private final String PREPAREDSTATEMENT_ERROR = "SQL PreparedStatement Error: \n";
    private final String SQL_ERROR = "SQL Error: \n";
    private Connection conn;
    private PreparedStatement ps;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public PersonneDAOEmplt(Connection conn) throws SQLException {

        this.conn = conn;
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
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setInt(1, id);
            try (ResultSet rs = this.ps.executeQuery()) {
                return rs.next() ? new Personne(rs.getInt("id"),
                        rs.getInt("id_status"),
                        rs.getString("nom"),
                        rs.getString("prenom")) : null;
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
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
        String sqlQuery = "SELECT id, id_status, nom, prenom FROM Personne";
        ArrayList<Personne> listOfPersons = new ArrayList<Personne>();
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            try (ResultSet rs = this.ps.executeQuery()) {
                while (rs.next()) {
                    Personne personne = new Personne(rs.getInt("id"),
                            rs.getInt("id_status"),
                            rs.getString("nom"),
                            rs.getString("prenom"));
                    listOfPersons.add(personne);
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
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

        return p.getId() > 0 ? insert(p) : update(p);
    }

    @Override
    public int insert(Personne person) throws SQLException {

        String sqlQuery = "INSERT INTO Personne (id_status, nom, prenom)" +
                "VALUES (?, ?, ?)";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            ps.setInt(1, person.getId_status());
            ps.setString(2, person.getNom());
            ps.setString(3, person.getPrenom());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }

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

        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            ps.setInt(1, p.getId_status());
            ps.setString(2, p.getNom());
            ps.setString(3, p.getPrenom());
            ps.setInt(4, p.getId());

            int rowAffected = ps.executeUpdate();

            return rowAffected > 0 ? rowAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
        }
        return 0;
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

        String sqlQuery = "DELETE FROM Personne WHERE id = ?";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);
            ps.setInt(1, p.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;
        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }

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
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
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
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            this.ps.setString(1, nom);
            this.ps.setString(2, prenom);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(RESULTSET_ERROR + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(SQL_ERROR + e.getMessage());
        }
        return id;

    }

    @Override
    public int insert(String status, String nom, String prenom) throws SQLException {
       String sqlQuery = "INSERT INTO Personne (id_status, nom, prenom)" +
                "VALUES ((SELECT id FROM status WHERE status = ?), ?, ?)";
        try {
            this.ps = this.conn.prepareStatement(sqlQuery);

            ps.setString(1, status);
            ps.setString(2, nom);
            ps.setString(3, prenom);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? rowsAffected : 0;

        } catch (SQLException e) {
            System.out.println(PREPAREDSTATEMENT_ERROR + e.getMessage());
            return 0;
        }
    }

}
