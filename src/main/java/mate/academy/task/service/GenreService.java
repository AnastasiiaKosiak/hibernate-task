package mate.academy.task.service;

import mate.academy.task.model.Genre;

import java.util.List;

public interface GenreService {
    Genre add(Genre genre);

    List<Genre> getAll();
}
