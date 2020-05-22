package mate.academy.task;

import mate.academy.task.lib.Injector;
import mate.academy.task.model.Author;
import mate.academy.task.model.Book;
import mate.academy.task.model.Genre;
import mate.academy.task.service.AuthorService;
import mate.academy.task.service.BookService;
import mate.academy.task.service.GenreService;

import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.task");

    public static void main(String[] args) {
        BookService bookService = (BookService)injector.getInstance(BookService.class);
        Author author = new Author();
        author.setName("Lesya Ukraiinka");
        AuthorService authorService = (AuthorService)injector.getInstance(AuthorService.class);
        authorService.add(author);
        authorService.getAll().forEach(System.out::println);
        GenreService genreService = (GenreService)injector.getInstance(GenreService.class);
        Genre genre = new Genre();
        genre.setGenre("poetry");
        genreService.add(genre);
        genreService.getAll().forEach(System.out::println);
        Book book = new Book();
        book.setAuthors(List.of(author));
        book.setGenre(genre);
        book.setTitle("Lisova pisnya");
        bookService.add(book);
        bookService.getAll().forEach(System.out::println);
        bookService.getBooksByGenre(genre).forEach(System.out::println);
        System.out.println(bookService.getByTitle("Lisova pisnya").toString());
        bookService.getBooksByAuthor(author).forEach(System.out::println);
    }
}
