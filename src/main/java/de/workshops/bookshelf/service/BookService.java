package de.workshops.bookshelf.service;

import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookNotFoundException;
import de.workshops.bookshelf.persistence.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        return repository.getAllBooks().stream()
                .filter(book -> hasIsbn(book, isbn)).findFirst()
                .orElseThrow(() -> new BookNotFoundException("ISBN: " + isbn));
    }

    public List<Book> findBooksByAuthor(String author) {
        return repository.getAllBooks().stream().filter(book -> hasAuthor(book, author)).toList();
    }

    public List<Book> findBooksByIsbnAndAuthor(String isbn, String author) {
        return repository.getAllBooks().stream()
                .filter(book -> isbn == null || hasIsbn(book, isbn))
                .filter(book -> author == null || hasAuthor(book, author))
                .toList();
    }

    private boolean hasIsbn(Book book, String isbn) {
        return book.getIsbn().equals(isbn);
    }

    private boolean hasAuthor(Book book, String author) {
        return book.getAuthor().contains(author);
    }
}
