package mate.academy.task.service.impl;

import mate.academy.task.dao.BookDao;
import mate.academy.task.lib.Inject;
import mate.academy.task.lib.Service;
import mate.academy.task.model.Author;
import mate.academy.task.model.Book;
import mate.academy.task.model.Genre;
import mate.academy.task.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return bookDao.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookDao.getBooksByGenre(genre);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
