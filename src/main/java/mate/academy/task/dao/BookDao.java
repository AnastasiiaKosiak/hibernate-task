package mate.academy.task.dao;

import mate.academy.task.model.Author;
import mate.academy.task.model.Book;
import mate.academy.task.model.Genre;

import java.util.List;

public interface BookDao {
    Book add(Book book);

    Book getByTitle(String title);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByGenre(Genre genre);

    List<Book> getAll();
}
