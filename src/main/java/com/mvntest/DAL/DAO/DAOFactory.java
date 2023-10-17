package com.mvntest.DAL.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mvntest.DAL.Cours.CoursDAOEmplt;
import com.mvntest.DAL.Cours_Personne.Cours_PersonneDAOEmplt;
import com.mvntest.DAL.Personne.PersonneDAOEmplt;
import com.mvntest.DAL.Section.SectionDAOEmplt;
import com.mvntest.DAL.Status.StatusDAOEmplt;

public class DAOFactory {

    public DAO<?> getDAO(DAOType daoType, Connection conn) throws SQLException {

        switch (daoType) {
            case STATUS:
                return new StatusDAOEmplt(conn);
            case COURS:
                return new CoursDAOEmplt(conn);
            case SECTION:
                return new SectionDAOEmplt(conn);
            case PERSONNE:
                return new PersonneDAOEmplt(conn);
            case COURS_PERSONNE:
                return new Cours_PersonneDAOEmplt(conn);

            default:
                throw new IllegalArgumentException("DAO type not supported: " + daoType);
        }
    }
}
