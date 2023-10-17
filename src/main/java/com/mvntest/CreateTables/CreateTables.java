package com.mvntest.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateTables {

    /**
     * Crée la table Cours dans la base de données.
     * 
     * @throws SQLException si une erreur SQL survient
     */
    public void cours(Connection conn) throws SQLException {

        String sqlQuery = "CREATE TABLE IF NOT EXISTS Cours ("
                + " id SERIAL PRIMARY KEY,"
                + " id_section INTEGER NOT NULL,"
                + " nom VARCHAR(30) UNIQUE,"
                + " CONSTRAINT fk_section FOREIGN KEY (id_section) REFERENCES section(id))";

        conn.createStatement().execute(sqlQuery);

    }

    public void status(Connection conn) throws SQLException {

        String sqlQuery = "CREATE TABLE IF NOT EXISTS Status ("
                + "id SERIAL PRIMARY KEY NOT NULL,"
                + "status VARCHAR(30) UNIQUE"
                + ")";
        conn.createStatement().execute(sqlQuery);
    }

    public void section(Connection conn) throws SQLException {

        String sqlQuery = "CREATE TABLE IF NOT EXISTS Section ("
                + "id SERIAL PRIMARY KEY,"
                + "nom VARCHAR(30)"
                + ")";
        conn.createStatement().execute(sqlQuery);

    }

    public void personne(Connection conn) throws SQLException {

        String sqlQuery = "CREATE TABLE IF NOT EXISTS Personne ("
                + "id SERIAL PRIMARY KEY NOT NULL,"
                + "id_status INTEGER NOT NULL,"
                + "nom VARCHAR(30),"
                + "prenom VARCHAR(30),"
                + "FOREIGN KEY (id_status) REFERENCES Status(id),"
                + "UNIQUE (nom, prenom)"
                + ")";

        conn.createStatement().execute(sqlQuery);

    }

    /**
     * Crée la table Cours_Personne dans la base de données.
     * 
     * @throws SQLException si une erreur SQL survient
     */
    public void coursPersonne(Connection conn) throws SQLException {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Cours_Personne ("
                + " id_personne INTEGER NOT NULL,"
                + " id_cours INTEGER NOT NULL,"
                + " annee INTEGER NOT NULL,"
                + " PRIMARY KEY (id_personne, id_cours, annee),"
                + " FOREIGN KEY (id_personne) REFERENCES Personne(id),"
                + " FOREIGN KEY (id_cours) REFERENCES Cours(id)"
                + " )";
        conn.createStatement().execute(createTableSQL);

    }

}
