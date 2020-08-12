package dao;

import domain.Position;
import domain.Translation;
import domain.Vocabulary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDAOImpl implements PositionDAO {

    private SessionFactory sessionFactory;

    @Override
    public void add(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(position);
    }

    @Override
    public Position getFromAllVocabularies(String source) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Position.class, source);
    }

    @Override
    public Position getFromVocabulary(String source, Vocabulary vocabulary) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("FROM Position P WHERE P.source = :source AND P.vocabulary = :vocabulary");
        query.setParameter("source", source);
        query.setParameter("vocabulary", vocabulary);
        return (Position) query.uniqueResult();
    }

    @Override
    public Translation getTranslationById(int translationId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Translation.class, translationId);
    }

    @Override
    public void delete(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(position);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}