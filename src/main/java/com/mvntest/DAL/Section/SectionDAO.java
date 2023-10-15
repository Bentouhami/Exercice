package com.mvntest.DAL.Section;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface SectionDAO extends DAO<Section> {

    // to add anything special for the Section entity
        void createSectionTB() throws SQLException;
        int getID(String s) throws SQLException;
        int deleteSection(String nom) throws SQLException;
}
