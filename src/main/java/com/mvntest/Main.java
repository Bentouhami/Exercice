package com.mvntest;

import java.sql.SQLException;
import com.mvntest.DAL.Status.Status;
import com.mvntest.DAL.Status.StatusDAO;
import com.mvntest.DAL.Status.StatusDAOEmplt;

public class Main {
    public static void main(String[] args) throws SQLException {
        
        //Section section = new Section(2, "testSection");
       // System.out.println(section);
        //SectionDAO s = new SectionDAOEmplt();

        //insert
        /*  try {
            int result = s.insert(section);
            System.out.println(result);
        } catch (SQLException e) {
            
            e.printStackTrace();
        } */

        //delete
        /* try {
            s.delete(4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } */

        // get
        /* Section newS = s.get(1);
        System.out.println(newS); */

        //getAll
        /* ArrayList<Section> sections = s.getAll();
        for (Section ss : sections) {
            System.out.println(ss);
        } */

        //Cours entity
        // get
         //CoursDAO c = new CoursDAOEmplt();
       /* System.out.println(c.get(1)); */

        //getAll
        /* ArrayList<Cours> coursList = c.getAll();
        for (Cours cour : coursList) {
            System.out.println(cour);
        } */

        // delete
        /* int row = c.delete(9);
        System.out.println(row + " row effected."); */
        //console result: 1 row effected.
        
        // update
        /* int row = c.update(new Cours(8,1, "StarUML"));
        System.out.println(row + " row effected.");  */

        //Status
        StatusDAO status = new StatusDAOEmplt();
        
        // insert
        Status s = new  Status(0, "Etudiant");
       // status.insert(s);
        Status s1 = new  Status(0, "Charge de cours");
        Status s2 = new  Status(0, "Employe administratif");
        
        int row = status.insert(s);
        int row1 = status.insert(s1);
        int row2 =status.insert(s2);
        System.out.println("s " + row + "s1 " + row1 + "s2 " + row2);





}  
}