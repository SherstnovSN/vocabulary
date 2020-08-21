package dao;

import domain.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageDAOImpl implements LanguageDAO {

    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Language> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Language").list();
    }

    @Override
    public Language getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Language.class, id);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
