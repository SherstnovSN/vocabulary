package dao;

import domain.Position;
import domain.Translation;
import domain.Vocabulary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAOImpl implements PositionDAO {

    private SessionFactory sessionFactory;

    @Override
    public void add(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(position);
    }

    @Override
    public Position getPositionById(int positionId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Position.class, positionId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> getFromAllVocabulariesBySource(String source) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("FROM Position P WHERE P.source = :source");
        query.setParameter("source", source);
        return (List<Position>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> getFromAllVocabulariesByTranslation(String translation) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("SELECT P FROM Position P JOIN Translation T ON P.id = T.position.id AND T.word = :translation");
        query.setParameter("translation", translation);
        return (List<Position>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> getFromVocabularyBySource(String source, Vocabulary vocabulary) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("FROM Position P WHERE P.source = :source AND P.vocabulary = :vocabulary");
        query.setParameter("source", source);
        query.setParameter("vocabulary", vocabulary);
        return (List<Position>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> getFromVocabularyByTranslation(String translation, Vocabulary vocabulary) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("SELECT P FROM Position P JOIN Translation T ON P.id = T.position.id AND T.word = :translation AND P.vocabulary = :vocabulary");
        query.setParameter("translation", translation);
        query.setParameter("vocabulary", vocabulary);
        return (List<Position>) query.list();
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