package homework.dao.hibernateDaoImpl;

import homework.model.entities.Project;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static homework.model.utils.HibernateUtils.getSession;

public class HibernateProjectDaoImpl extends HibernateDaoImpl<Integer, Project> {
    @Override
    public Project read(Integer key) {
        Project project = null;
        try {
            Transaction transaction = getSession().beginTransaction();

            project = getSession().find(Project.class, key);

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return project;
    }

    @Override
    public Collection<Project> read() {
        List<Project> projects = new ArrayList<>();
        try {
            Transaction transaction = getSession().beginTransaction();

            projects = getSession().createQuery("FROM Project ").list();

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return projects;
    }
}
