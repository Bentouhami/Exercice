package com.mvntest.DAL.Status;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface StatusDAO extends DAO<Status> {

    int getID(String s) throws SQLException;

    int delete(String string) throws SQLException;
    int insert(String status) throws SQLException;
}
