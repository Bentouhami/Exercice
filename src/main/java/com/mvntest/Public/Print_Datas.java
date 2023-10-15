package com.mvntest.Public;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mvntest.DAL.Cours.Cours;
import com.mvntest.DAL.Cours.CoursDAO;
import com.mvntest.DAL.Cours_Personne.Cours_Personne;
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAO;
import com.mvntest.DAL.Personne.Personne;
import com.mvntest.DAL.Personne.PersonneDAO;
import com.mvntest.DAL.Section.Section;
import com.mvntest.DAL.Section.SectionDAO;
import com.mvntest.DAL.Status.Status;
import com.mvntest.DAL.Status.StatusDAO;

public class Print_Datas {

    public void print(StatusDAO status, SectionDAO sections, CoursDAO cours, PersonneDAO presonnes,
            Cours_PersonneDAO cours_personnes) {

        System.out.println("This is a test");

        System.out.println("Status datas:\n");
        try {
            ArrayList<Status> statusList = status.getAll();
            for (Status s : statusList) {
                System.out.println(s);
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Cant't finish printing datas ! " + e.getMessage());
        }


        System.out.println("Sections datas:\n");
        try {
            ArrayList<Section> sectionList = sections.getAll();
            for (Section s : sectionList) {
                System.out.println(s);
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Cant't finish printing datas ! " + e.getMessage());
        }
    
        
        System.out.println("Cours datas:\n");
        try {
            ArrayList<Cours> coursList = cours.getAll();
            for (Cours c : coursList) {
                System.out.println(c);
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Cant't finish printing datas ! " + e.getMessage());
        }
    
        
        System.out.println("Personnes datas:\n");
        try {
            ArrayList<Personne> presonnesList = presonnes.getAll();
            for (Personne p : presonnesList) {
                System.out.println(p);
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Cant't finish printing datas ! " + e.getMessage());
        }
    
        
        System.out.println("Cours_Personne datas:\n");
        try {
            ArrayList<Cours_Personne> cours_personnesList = cours_personnes.getAll();
            for (Cours_Personne cours_Personne : cours_personnesList) {
                System.out.println(cours_Personne);
            }
            System.out.println("Datas printing finished.");

        } catch (SQLException e) {
            System.out.println("Cant't finish printing datas ! " + e.getMessage());
        }
        
    }

}
