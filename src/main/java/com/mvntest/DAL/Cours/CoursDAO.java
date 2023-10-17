package com.mvntest.DAL.Cours;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface CoursDAO extends DAO<Cours> {

        int getID(int id_section, String s) throws SQLException;
<<<<<<< HEAD

        int getID(String s) throws SQLException;

        int insert(String sectionName, String coursNom) throws SQLException;
=======
        int getID(String s) throws SQLException;
        int insert(int id_section, String coursNom) throws SQLException;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
}
