package com.mvntest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.mvntest.CreateTables.CreateTables;
import com.mvntest.DAL.Cours.CoursDAO;
import com.mvntest.DAL.Cours.CoursDAOEmplt;
<<<<<<< HEAD
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAO;
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAOEmplt;
import com.mvntest.DAL.DAO.DAOFactory;
import com.mvntest.DAL.DAO.DAOType;
=======
import com.mvntest.DAL.Cours_Personne.Cours_Personne;
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAO;
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAOEmplt;
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
import com.mvntest.DAL.Personne.PersonneDAO;
import com.mvntest.DAL.Personne.PersonneDAOEmplt;
import com.mvntest.DAL.Section.SectionDAO;
import com.mvntest.DAL.Section.SectionDAOEmplt;
import com.mvntest.DAL.Status.StatusDAO;
import com.mvntest.DAL.Status.StatusDAOEmplt;
import com.mvntest.DBConfig.Database;
import com.mvntest.Public.Print_Datas;

public class Main {
  public static void main(String[] args) {

<<<<<<< HEAD
        Connection connexion = null;

        // Gettindabag ready the connection to our SGBD and settling up our Database.
        try {
            connexion = Database.getConnection();

            System.out.println("Connected to the SGBD");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Scanner sc = new Scanner(System.in);

            String dbName = "";

            while (true) {
                System.out.print("Enter database name: ");
                dbName = sc.nextLine().trim();

                if (dbName.isEmpty()) {
                    System.out.println("Database name cannot be empty. Try again.");
                } else if (dbName.matches("\\d+")) {
                    System.out.println("Database name cannot be a number. Try again.");
                } else if (dbName.contains(" ")) {
                    System.out.println("Database name cannot contain spaces. Try again.");
                } else {
                    break; 
                }
            }

            sc.close();

            connexion = Database.getConnection(dbName);

            DAOFactory daoFactory = new DAOFactory();
            CreateTables createTables = new CreateTables();

            // create tables
            createTables.status(connexion);
            createTables.section(connexion);
            createTables.cours(connexion);
            createTables.personne(connexion);
            createTables.coursPersonne(connexion);

            // getting datas raedy to be inserted

            StatusDAO statusDAO = (StatusDAOEmplt) daoFactory.getDAO(DAOType.STATUS, connexion);
            // Insertion des valeurs dans la table Status
            int statusRowsAffected = statusDAO.insert("Charge de cours");
            statusRowsAffected += statusDAO.insert("Employe administratif");
            statusRowsAffected += statusDAO.insert("Etudiant");

            System.out.println("Status rows affected : " + statusRowsAffected);

            SectionDAO sectionDAO = (SectionDAOEmplt) daoFactory.getDAO(DAOType.SECTION, connexion);
            // Insertion des valeurs dans la table Section
            int sectionRowsAffected = sectionDAO.insert("Informatiques de gestion");
            sectionRowsAffected += sectionDAO.insert("Droit");
            System.out.println("Section rows affected : " + sectionRowsAffected);

            CoursDAO coursDAO = (CoursDAOEmplt) daoFactory.getDAO(DAOType.COURS, connexion);
            // Insertion des valeurs dans la table Cours.
            int coursRowsAffected = coursDAO.insert("Informatiques de gestion", "Base de réseaux");
            coursRowsAffected += coursDAO.insert("Informatiques de gestion", "Systèmes d''exploitation");
            coursRowsAffected += coursDAO.insert("Informatiques de gestion", "Programmation orienté objet");
            coursRowsAffected += coursDAO.insert("Droit", "Droit civil");
            coursRowsAffected += coursDAO.insert("Droit", "Droit commercial");

            System.out.println("Cours rows affected : " + coursRowsAffected);

            PersonneDAO personneDAO = (PersonneDAOEmplt) daoFactory.getDAO(DAOType.PERSONNE, connexion);
            // Insertion des valeurs dans la table Personne.
            int personneRowsAffected = personneDAO.insert("Charge de cours", "Poulet", "Gilles");
            personneRowsAffected += personneDAO.insert("Charge de cours", "Godissart", "Emmanuel");
            personneRowsAffected += personneDAO.insert("Employe administratif", "Lai", "Valeria");
            personneRowsAffected += personneDAO.insert("Employe administratif", "Mairesse", "David");
            personneRowsAffected += personneDAO.insert("Etudiant", "Durant", "Richard");
            personneRowsAffected += personneDAO.insert("Etudiant", "Ortiz", "Valerie");

            System.out.println("Personne rows affected: " + personneRowsAffected);

            Cours_PersonneDAO cours_PersonneDAO = (Cours_PersonneDAOEmplt) daoFactory.getDAO(DAOType.COURS_PERSONNE,
                    connexion);
            // Insertion des valeurs dans la table Cours_Personne
            int coursPersonneRowsAffected = cours_PersonneDAO.insert("Poulet", "Gilles", "Systèmes d''exploitation",
                    2018);
            coursPersonneRowsAffected += cours_PersonneDAO.insert("Godissart", "Emmanuel", "Base de réseaux", 2016);
            coursPersonneRowsAffected += cours_PersonneDAO.insert("Durant", "Richard", "Systèmes d''exploitation",
                    2020);
            coursPersonneRowsAffected += cours_PersonneDAO.insert("Durant", "Richard", "Base de réseaux", 2022);

            System.out.println("Cours Personne rows affected: " + coursPersonneRowsAffected);

            Print_Datas print = new Print_Datas();
            print.print(statusDAO, sectionDAO, coursDAO, personneDAO, cours_PersonneDAO);

            Database.closeConnection(connexion);
        } catch (SQLException e) {
            System.err.println("An error occurred while accessing the database: " +
                    e.getMessage());
        } finally {
            Database.closeConnection(connexion);
        }

=======
    try {
      StatusDAO statusDAO = new StatusDAOEmplt();
      PersonneDAO personneDAO = new PersonneDAOEmplt();
      CoursDAO coursDAO = new CoursDAOEmplt();
      SectionDAO sectionDAO = new SectionDAOEmplt();
      Cours_PersonneDAO coursPersonneDAO = new Cours_PersonneDAOEmplt();

      StringBuilder separatorBuilder = new StringBuilder("-");
for (int i = 0; i < 175; i++) {
  separatorBuilder.append("-");
}
String separator = separatorBuilder.toString();
      System.err.println("\n");
      System.out.println("Databa datas:");
      System.err.println(separator);
      System.out.println(" status : " + statusDAO.getAll());
      System.err.println(separator);
      System.out.println(" personnes : " + personneDAO.getAll());
      System.err.println(separator);
      System.out.println("cours : " + coursDAO.getAll());
      System.err.println(separator);
      System.out.println(" section : " + sectionDAO.getAll());
      System.err.println(separator);
      System.out.println("cours personnes : " + coursPersonneDAO.getAll());
      System.err.println("\nTHE END.");
      System.err.println(separator);
    } catch (SQLException e) {
      System.err.println("An error occurred while accessing the database: " + e.getMessage());
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    }
  }
}
