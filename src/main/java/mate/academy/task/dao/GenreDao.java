package mate.academy.task.dao;

import mate.academy.task.model.Genre;

import java.util.List;

public interface GenreDao {
    Genre add(Genre genre);

    List<Genre> getAll();
}
