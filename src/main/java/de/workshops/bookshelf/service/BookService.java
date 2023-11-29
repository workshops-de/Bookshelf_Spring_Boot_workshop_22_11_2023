package de.workshops.bookshelf.service;

import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookNotFoundException;
import de.workshops.bookshelf.persistence.BookRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        return repository.findBookByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("ISBN: " + isbn));
    }

    public List<Book> findBooksByAuthor(String author) {
        return repository.findBooksByAuthorContains(author);
    }

    public List<Book> findBooksByIsbnAndAuthor(String isbn, String author) {
        return repository.findBooksByIsbnAndAuthor(isbn, author);
    }
}
