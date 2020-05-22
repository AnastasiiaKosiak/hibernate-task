package mate.academy.task.dao.impl;

import mate.academy.task.dao.BookDao;
import mate.academy.task.exceptions.DataProcessingException;
import mate.academy.task.lib.Dao;
import mate.academy.task.model.Author;
import mate.academy.task.model.Book;
import mate.academy.task.model.Genre;
import mate.academy.task.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.List;

@Dao
public class BookDaoImpl implements BookDao {
    @Override
    public Book add(Book book) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long bookId = (Long) session.save(book);
            transaction.commit();
            book.setId(bookId);
            return book;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert a Book entity", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book getByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root).where(criteriaBuilder.equal(root.get("title"), title));
            return session.createQuery(query).uniqueResult();
        } catch (Exception exception) {
            throw new DataProcessingException("Error getting a books by its title", exception);
        }
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root).where(criteriaBuilder.isMember(author, root.get("authors")));
            return session.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw new DataProcessingException("Error getting a list of books by author", exception);
        }
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root).where(criteriaBuilder.equal(root.get("genre"), genre.getId()));
            return session.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw new DataProcessingException("Error getting a list of books by genre", exception);
        }
    }

    @Override
    public List<Book> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Book.class);
            criteriaQuery.from(Book.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            throw new DataProcessingException("Error getting a list of all books", exception);
        }
    }
}
