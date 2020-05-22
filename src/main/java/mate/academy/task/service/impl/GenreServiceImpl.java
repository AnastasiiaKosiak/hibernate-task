package mate.academy.task.service.impl;

import mate.academy.task.dao.GenreDao;
import mate.academy.task.lib.Inject;
import mate.academy.task.lib.Service;
import mate.academy.task.model.Genre;
import mate.academy.task.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
