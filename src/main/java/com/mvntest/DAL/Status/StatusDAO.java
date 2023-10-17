package com.mvntest.DAL.Status;

import java.sql.SQLException;

import com.mvntest.DAL.DAO.DAO;

public interface StatusDAO extends DAO<Status> {

<<<<<<< HEAD
    int getID(String s) throws SQLException;

    int delete(String string) throws SQLException;
    int insert(String status) throws SQLException;
=======

            void createStatusTB() throws SQLException;
            int getID(String s) throws SQLException;
            int delete(String string) throws SQLException;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
}
