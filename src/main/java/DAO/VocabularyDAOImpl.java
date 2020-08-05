package DAO;

import domain.Vocabulary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VocabularyDAOImpl implements VocabularyDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Vocabulary> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Vocabulary").list();
    }

    @Override
    public Vocabulary getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Vocabulary.class, id);
    }

}
