package dao;

import domain.Language;
import domain.Position;
import domain.Translation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDAOImpl implements PositionDAO {

    private SessionFactory sessionFactory;

    @Override
    public void addPosition(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(position);
    }

    @Override
    public Position getPositionById(int positionId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Position.class, positionId);
    }

    @Override
    public Position getPositionBySourceAndLanguage(String source, Language sourceLanguage) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("FROM Position P WHERE P.source = :source AND P.language = :sourceLanguage");
        query.setParameter("source", source);
        query.setParameter("sourceLanguage", sourceLanguage);
        return (Position) query.uniqueResult();
    }

    @Override
    public Translation getTranslationById(int translationId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Translation.class, translationId);
    }

    @Override
    public Translation getTranslationByWordAndLanguage(String word, Language translationLanguage) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("FROM Translation T WHERE T.word = :word AND T.language = :translationLanguage");
        query.setParameter("word", word);
        query.setParameter("translationLanguage", translationLanguage);
        return (Translation) query.uniqueResult();
    }

    @Override
    public void deletePosition(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(position);
    }

    @Override
    public void deleteTranslation(Translation translation) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(translation);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}