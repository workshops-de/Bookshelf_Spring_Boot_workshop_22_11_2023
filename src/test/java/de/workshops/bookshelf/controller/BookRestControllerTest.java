package de.workshops.bookshelf.controller;

import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookTestData;
import de.workshops.bookshelf.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookRestControllerTest {

    @Autowired
    BookRestController bookRestController;

    @MockBean
    BookService bookService;

    List<Book> knownBooks = BookTestData.knownBooks();

    @Test
    void getAllBooks_returns_all_books() {
        when(bookService.getAllBooks()).thenReturn(knownBooks);

        List<Book> books = bookRestController.getAllBooks();
        assertThat(books).hasSize(3);
    }
}
