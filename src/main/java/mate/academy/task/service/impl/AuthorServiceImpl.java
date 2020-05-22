package mate.academy.task.service.impl;

import mate.academy.task.dao.AuthorDao;
import mate.academy.task.lib.Inject;
import mate.academy.task.lib.Service;
import mate.academy.task.model.Author;
import mate.academy.task.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
