package com.mvntest.DAL.Cours;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface CoursDAO extends DAO<Cours> {

        int getID(int id_section, String s) throws SQLException;

        int getID(String s) throws SQLException;

        int insert(String sectionName, String coursNom) throws SQLException;
}
