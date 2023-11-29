package de.workshops.bookshelf.controller;

import de.workshops.bookshelf.config.BookshelfUser;
import de.workshops.bookshelf.config.BookshelfUserRepository;
import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookTestData;
import de.workshops.bookshelf.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BookRestControllerRestTest {

    BookshelfUser user;

    RestClient restClient;

    @MockBean
    BookService bookService;

    @Autowired
    BookshelfUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    List<Book> knownBooks = BookTestData.knownBooks();

    @BeforeEach
    void setUp(@LocalServerPort int port) {
        String username = "mock_user";
        String password = "secret";
        user = userRepository.save(new BookshelfUser(username, passwordEncoder.encode(password)));

        restClient = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .defaultHeaders(headers -> headers.setBasicAuth(username, password))
                .build();

        when(bookService.getAllBooks()).thenReturn(knownBooks);
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(user);
    }

    @Test
    void getAllBooks_returns_all_book_objects() {
        ResponseEntity<Book[]> booksResponse = restClient.get().uri("/book").retrieve().toEntity(Book[].class);
        assertThat(booksResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Book[] books = booksResponse.getBody();
        assertThat(books).hasSameSizeAs(knownBooks);
    }
}
