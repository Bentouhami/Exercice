package com.mvntest.DAL.Section;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface SectionDAO extends DAO<Section> {

    // to add anything special for the Section entity
    int getID(String s) throws SQLException;

    int deleteSection(String nom) throws SQLException;
    int insert(String section) throws SQLException;
}
