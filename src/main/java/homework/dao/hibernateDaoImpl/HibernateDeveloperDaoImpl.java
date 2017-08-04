package homework.dao.hibernateDaoImpl;

import homework.model.entities.Developer;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static homework.model.utils.HibernateUtils.getSession;

public class HibernateDeveloperDaoImpl extends HibernateDaoImpl<Integer, Developer> {
    @Override
    public Developer read(Integer key) {
        Developer developer = null;
        try {
            Transaction transaction = getSession().beginTransaction();

            developer = getSession().find(Developer.class, key);

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return developer;
    }

    @Override
    public Collection<Developer> read() {
        List<Developer> developers = new ArrayList<>();
        try {
            Transaction transaction = getSession().beginTransaction();

            developers = getSession().createQuery("FROM Developer ").list();

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return developers;
    }
}
