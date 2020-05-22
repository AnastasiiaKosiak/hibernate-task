package mate.academy.task.dao;

import mate.academy.task.model.Author;

import java.util.List;

public interface AuthorDao {
    Author add(Author author);

    List<Author> getAll();
}
