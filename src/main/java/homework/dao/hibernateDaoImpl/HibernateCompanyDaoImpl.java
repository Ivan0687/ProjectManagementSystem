package homework.dao.hibernateDaoImpl;

import homework.model.entities.Company;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static homework.model.utils.HibernateUtils.getSession;

public class HibernateCompanyDaoImpl extends HibernateDaoImpl<Integer, Company> {
    @Override
    public Company read(Integer key) {
        Company company = null;
        try {
            Transaction transaction = getSession().beginTransaction();

            company = getSession().find(Company.class, key);

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return company;
    }

    @Override
    public Collection<Company> read() {
        List<Company> companies = new ArrayList<>();
        try {
            Transaction transaction = getSession().beginTransaction();

            companies = getSession().createQuery("FROM Company ").list();

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return companies;
    }
}
