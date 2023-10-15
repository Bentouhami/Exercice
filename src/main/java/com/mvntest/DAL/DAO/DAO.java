package com.mvntest.DAL.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * generique class
 */
public interface DAO<T> {

    T get(int id) throws SQLException;

    int delete(int id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    int save(T t) throws SQLException;

    int insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    int delete(T t) throws SQLException;

}
