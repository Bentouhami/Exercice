package com.mvntest.DAL.Section;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface SectionDAO extends DAO<Section> {

    // to add anything special for the Section entity
<<<<<<< HEAD
    int getID(String s) throws SQLException;

    int deleteSection(String nom) throws SQLException;
    int insert(String section) throws SQLException;
=======
        void createSectionTB() throws SQLException;
        int getID(String s) throws SQLException;
        int deleteSection(String nom) throws SQLException;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
}
