package com.mvntest.DAL.Personne;
import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface PersonneDAO extends DAO<Personne> {

    // to add anything speciel for the Personne Entity
        void createPersonneTB() throws SQLException;
        int getID(String nom, String prenom) throws SQLException;

}
