package homework.dao.hibernateDaoImpl;

import homework.model.entities.Skill;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static homework.model.utils.HibernateUtils.getSession;

public class HibernateSkillDaoImpl extends HibernateDaoImpl<Integer, Skill> {

    @Override
    public Skill read(Integer id) {

        Skill skill = null;
        try {
            Transaction transaction = getSession().beginTransaction();

            skill = getSession().find(Skill.class, id);

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return skill;
    }

    @Override
    public Collection<Skill> read() {

        List<Skill> skills = new ArrayList<>();
        try {
            Transaction transaction = getSession().beginTransaction();

            //skills = getSession().createCriteria(Skill.class).list();
            skills = getSession().createQuery("FROM Skill").list();

            transaction.commit();

        } catch (Exception e) {
            if (getSession().getTransaction() != null)
                getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {

            getSession().close();
        }

        return skills;
    }

}
