package homework.dao.hibernateDaoImpl;

import homework.model.entities.Customer;

import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static homework.model.utils.HibernateUtils.getSession;

public class HibernateCustomerDaoImpl extends HibernateDaoImpl<Integer, Customer> {


    @Override
    public Customer read(Integer key) {
        Customer customer = null;
        try {
            Transaction transaction = getSession().beginTransaction();

            customer = getSession().find(Customer.class, key);

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return customer;
    }

    @Override
    public Collection<Customer> read() {
        List<Customer> customers = new ArrayList<>();
        try {
            Transaction transaction = getSession().beginTransaction();

            customers = getSession().createQuery("FROM Customer ").list();

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return customers;
    }
}
