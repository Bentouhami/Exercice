package com.mvntest;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DAL.Cours.Cours;
import com.mvntest.DAL.Cours.CoursDAO;
import com.mvntest.DAL.Cours.CoursDAOEmplt;
import com.mvntest.DAL.Cours_Personne.Cours_Personne;
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAO;
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAOEmplt;
import com.mvntest.DAL.Personne.PersonneDAO;
import com.mvntest.DAL.Personne.PersonneDAOEmplt;
import com.mvntest.DAL.Section.SectionDAO;
import com.mvntest.DAL.Section.SectionDAOEmplt;
import com.mvntest.DAL.Status.StatusDAO;
import com.mvntest.DAL.Status.StatusDAOEmplt;

public class Main {
  public static void main(String[] args) {

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
    }
  }
}