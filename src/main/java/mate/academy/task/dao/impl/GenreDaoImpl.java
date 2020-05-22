package mate.academy.task.dao.impl;

import mate.academy.task.dao.GenreDao;
import mate.academy.task.exceptions.DataProcessingException;
import mate.academy.task.lib.Dao;
import mate.academy.task.model.Author;
import mate.academy.task.model.Genre;
import mate.academy.task.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Dao
public class GenreDaoImpl implements GenreDao {
    @Override
    public Genre add(Genre genre) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long genreId = (Long) session.save(genre);
            transaction.commit();
            genre.setId(genreId);
            return genre;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert a Genre entity", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Genre> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Genre> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Genre.class);
            criteriaQuery.from(Genre.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            throw new DataProcessingException("Error getting a list of all genres", exception);
        }
    }
}
