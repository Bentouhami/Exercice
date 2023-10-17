package com.mvntest.DAL.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * generique class
 */
public interface DAO<T> {

    T get(int id) throws SQLException;
<<<<<<< HEAD

=======
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    int delete(int id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    int save(T t) throws SQLException;
<<<<<<< HEAD

    int insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    int delete(T t) throws SQLException;

=======
    int insert (T t) throws SQLException ;
    int update (T t) throws SQLException ;
    int delete(T t) throws SQLException ;
    
    
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
}
