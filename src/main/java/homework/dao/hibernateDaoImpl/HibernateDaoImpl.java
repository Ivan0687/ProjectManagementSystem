package homework.dao.hibernateDaoImpl;

import homework.dao.basicDao.DAO;
import homework.model.entities.Model;
import org.hibernate.Transaction;


import static homework.model.utils.HibernateUtils.getSession;

public abstract class HibernateDaoImpl<K extends Integer, T extends Model> implements DAO<K, T> {

    @Override
    public void create(T entity) {
        try {
            Transaction transaction = getSession().beginTransaction();

            getSession().save(entity);

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }
    }

    @Override
    public void update(K key, T entity) {

        try {
            Transaction transaction = getSession().beginTransaction();

            entity.setId(key);
            getSession().merge(entity);

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

    }

    @Override
    public void delete(K key) {

        try {
            Transaction transaction = getSession().beginTransaction();

            getSession().delete(read(key));

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }
    }

}
