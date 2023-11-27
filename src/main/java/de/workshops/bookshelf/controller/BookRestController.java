package de.workshops.bookshelf.controller;

import de.workshops.bookshelf.config.BookshelfProperties;
import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookNotFoundException;
import de.workshops.bookshelf.service.BookService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {

    private final BookService service;

    private final BookshelfProperties properties;

    public BookRestController(BookService service, BookshelfProperties properties) {
        this.service = service;
        this.properties = properties;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public Book getSingleBookByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        return service.getBookByIsbn(isbn);

/*
        try {
            Book book = service.getBookByIsbn(isbn);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
*/
    }

    @GetMapping(params = "author")
    public List<Book> searchBooksByAuthor(@RequestParam @NotBlank @Size(min = 3) String author) {
        return service.findBooksByAuthor(author);
    }

    @PostMapping("/search")
    public List<Book> searchBooks(@RequestBody @Valid BookSearchRequest searchRequest) {
        return service.findBooksByIsbnAndAuthor(searchRequest.getIsbn(), searchRequest.getAuthor());
    }

    @GetMapping("/{isbn}/lookup")
    public String lookupIsbn(@PathVariable String isbn) {
        return String.format("%s does lookup for '%s' via %s with key '%s'",
                properties.getOwner(), isbn, properties.getIsbnLookup().getUrl(), properties.getIsbnLookup().getApiKey()
        );
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse handleBookNotFoundException(BookNotFoundException e) {
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, "Book not found - " + e.getMessage()).build();
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleConstraintViolationException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
