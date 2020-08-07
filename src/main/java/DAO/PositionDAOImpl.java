package DAO;

import domain.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public Position get(String source) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Position.class, source);
    }

    @Override
    public void delete(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(position);
    }

}
