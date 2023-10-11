package com.mvntest.DAL.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DBConfig.Database;

public class PersonneDAOEmplt implements PersonneDAO {


    public PersonneDAOEmplt() throws SQLException {
        String sqlQuery =   "CREATE TABLE IF NOT EXISTS Personne ( "+
                                "id SERIAL PRIMARY KEY,"+
                                "INT id_status,"+
                                "nom VARCHAR(15),"+
                                "prenom VARCHAR(15)"+
                            ")";

        try (Connection conn = Database.getConnection();
                PreparedStatement st = conn.prepareStatement(sqlQuery)) {
                    boolean rs = st.execute();
                    String msg = rs == true ? "Table Created successfully." : "Failed to create Table.";
                    System.out.println(msg);
        }

    }
    @Override
    public Personne get(int id) {
        
        return null;
    }

    @Override
    public ArrayList<Personne> getAll() {
        
        return null;
    }

    @Override
    public int save(Personne t) {
        
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int insert(Personne t) {
        
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Personne t) {
        
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Personne t) {
        
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    @Override
    public int delete(int id) throws SQLException {
        
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    @Override
    public boolean isExists(int id, Connection conn) throws SQLException {
            String sqlQuery = "SELECT COUNT(*) FROM Personne_Cours WHERE id = ? ";
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
                ps.setInt(1,id);
                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()){
                        return rs.getInt(1) > 0;
                    }
                }
            }
            return false;        
        }
    @Override
    public boolean isExists(Personne p, Connection conn) throws SQLException {
            String sqlQuery = "SELECT COUNT(*) FROM Personne_Cours WHERE id = ? ";
            try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
                ps.setInt(1, p.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()){
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
    
}
