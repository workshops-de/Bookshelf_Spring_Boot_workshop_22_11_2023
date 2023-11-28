package de.workshops.bookshelf.persistence;

import de.workshops.bookshelf.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    void findAll_returns_books_from_database() {
        List<Book> books = repository.findAll();
        assertThat(books).isNotEmpty().allSatisfy(book -> {
            assertThat(book.getTitle()).isNotEmpty();
        });
    }

    @Test
    void findBooksByIsbnAndAuthor_ignores_with_null_parameter() {
        List<Book> books = repository.findBooksByIsbnAndAuthor(null, "Erich");
        assertThat(books).isNotEmpty().allSatisfy(book -> {
            assertThat(book.getAuthor()).contains("Erich");
        });
    }
}
