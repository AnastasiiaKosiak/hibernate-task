package mate.academy.task.dao.impl;

import mate.academy.task.dao.AuthorDao;
import mate.academy.task.exceptions.DataProcessingException;
import mate.academy.task.lib.Dao;
import mate.academy.task.model.Author;
import mate.academy.task.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Dao
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author add(Author author) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long authorId = (Long) session.save(author);
            transaction.commit();
            author.setId(authorId);
            return author;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert an Author entity", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Author> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Author> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Author.class);
            criteriaQuery.from(Author.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            throw new DataProcessingException("Error getting a list of all authors", exception);
        }
    }
}
