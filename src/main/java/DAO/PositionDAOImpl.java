package DAO;

import domain.Position;
import domain.Vocabulary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAOImpl implements PositionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(position);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Position").list();
    }

    @Override
    public Position getFromAllVocabularies(String source) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Position.class, source);
    }

    @Override
    public Position getFromVocabulary(String source, Vocabulary vocabulary) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Position P WHERE P.source = :source AND P.vocabulary = :vocabulary");
        query.setParameter("source", source);
        query.setParameter("vocabulary", vocabulary);
        return (Position) query.uniqueResult();
    }

    @Override
    public void edit(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.update(position);
    }

    @Override
    public void delete(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(position);
    }

}
