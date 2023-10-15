package com.mvntest.DAL.Status;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface StatusDAO extends DAO<Status> {


            void createStatusTB() throws SQLException;
            int getID(String s) throws SQLException;
            int delete(String string) throws SQLException;
}
