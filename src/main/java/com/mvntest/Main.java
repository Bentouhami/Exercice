package com.mvntest;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DAL.Cours.Cours;
import com.mvntest.DAL.Cours.CoursDAO;
import com.mvntest.DAL.Cours.CoursDAOEmplt;
import com.mvntest.DAL.Personne.Personne;
import com.mvntest.DAL.Personne.PersonneDAO;
import com.mvntest.DAL.Personne.PersonneDAOEmplt;
import com.mvntest.DAL.Section.SectionDAO;
import com.mvntest.DAL.Section.SectionDAOEmplt;
import com.mvntest.DAL.Status.Status;
import com.mvntest.DAL.Status.StatusDAO;
import com.mvntest.DAL.Status.StatusDAOEmplt;

public class Main {
    public static void main(String[] args) throws SQLException {

        // #region tests Section / Cours / Status
        // Section section = new Section(2, "testSection");
        // System.out.println(section);
        // SectionDAO s = new SectionDAOEmplt();

        // insert
        /*
         * try {
         * int result = s.insert(section);
         * System.out.println(result);
         * } catch (SQLException e) {
         * 
         * e.printStackTrace();
         * }
         */

        // delete
        /*
         * try {
         * s.delete(4);
         * } catch (SQLException e) {
         * System.out.println(e.getMessage());
         * }
         */

        // get
        /*
         * Section newS = s.get(1);
         * System.out.println(newS);
         */

        // getAll
        /*
         * ArrayList<Section> sections = s.getAll();
         * for (Section ss : sections) {
         * System.out.println(ss);
         * }
         */

        // Cours entity
        // get
        // CoursDAO c = new CoursDAOEmplt();
        /* System.out.println(c.get(1)); */

        // getAll
        /*
         * ArrayList<Cours> coursList = c.getAll();
         * for (Cours cour : coursList) {
         * System.out.println(cour);
         * }
         */

        // delete
        /*
         * int row = c.delete(9);
         * System.out.println(row + " row effected.");
         */
        // console result: 1 row effected.

        // update
        /*
         * int row = c.update(new Cours(8,1, "StarUML"));
         * System.out.println(row + " row effected.");
         */

        // Status
        // StatusDAO status = new StatusDAOEmplt();
        // Status stat = status.get(4);

        // getAll
        /*
         * stat.setStatus("Etudiant");
         * 
         * System.out.println(status.save(stat) + " row.");
         */

        // status.insert(new Status(0, "status"));

        // delete
        /*
         * status.delete(4);
         * ArrayList<Status> sst = status.getAll();
         * for (Status s : sst) {
         * System.out.println(s);
         * }
         */
        // #endregion
 
        //SectionDAO sectionDAO = new SectionDAOEmplt();    
        StatusDAO statusDAO = new StatusDAOEmplt();
        //CoursDAO coursDAO = new CoursDAOEmplt();
        PersonneDAO personneDAO = new PersonneDAOEmplt();

            
        int statusId1 = statusDAO.getID("Charge de cours");
        int statusId2 = statusDAO.getID("Employe administratif");
        int statusId3 = statusDAO.getID("Etudiant");

        /* Personne p1 = new Personne(0, statusId1, "Poulet", "Gilles");
        Personne p2 = new Personne(0, statusId1, "Godissart", "Emmanuel");
        Personne p3 = new Personne(0, statusId2, "Lai", "Valeria");
        Personne p4 = new Personne(0, statusId2, "Mairesse", "David");
        Personne p5 = new Personne(0, statusId3, "Durant", "Richard");
        Personne p6 = new Personne(0, statusId3, "Ortiz", "Valerie");
        
        personneDAO.insert(p1);
        personneDAO.insert(p2);
        personneDAO.insert(p3);
        personneDAO.insert(p4);
        personneDAO.insert(p5);
        personneDAO.insert(p6); */
        
        ArrayList<Personne> personneList = personneDAO.getAll();
        for (Personne personne : personneList) {
          System.out.println(personne.getNom() + "        " + personne.getPrenom()+ "      :  " + statusDAO.get(personne.getId_status()).getStatus());
        }
            
          /*   
            int coursId = coursDAO.getID("Droit civil");
            System.out.println(staustId); */
        
    }
}