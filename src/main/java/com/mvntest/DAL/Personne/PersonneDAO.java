package com.mvntest.DAL.Personne;
<<<<<<< HEAD

=======
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface PersonneDAO extends DAO<Personne> {

    // to add anything speciel for the Personne Entity
<<<<<<< HEAD
    int getID(String nom, String prenom) throws SQLException;
    int insert(String status, String nom, String prenom) throws SQLException;
=======
        void createPersonneTB() throws SQLException;
        int getID(String nom, String prenom) throws SQLException;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f

}
