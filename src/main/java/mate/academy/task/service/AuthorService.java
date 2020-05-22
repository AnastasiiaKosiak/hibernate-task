package mate.academy.task.service;

import jdk.dynalink.linker.LinkerServices;
import mate.academy.task.model.Author;

import java.util.List;

public interface AuthorService {
    Author add(Author author);

    List<Author> getAll();
}
