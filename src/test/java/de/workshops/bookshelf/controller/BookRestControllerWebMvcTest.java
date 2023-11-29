package de.workshops.bookshelf.controller;

import de.workshops.bookshelf.config.BookshelfProperties;
import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookTestData;
import de.workshops.bookshelf.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
@WithMockUser
class BookRestControllerWebMvcTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService bookService;

    @MockBean
    BookshelfProperties bookshelfProperties;

    List<Book> knownBooks = BookTestData.knownBooks();

    @BeforeEach
    void setUp() {
        when(bookService.getAllBooks()).thenReturn(knownBooks);
    }

    @Test
    void getAllBooks_returns_all_books_as_json() throws Exception {
        mvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(knownBooks.size())))
                .andExpect(jsonPath("$[1].title", is(knownBooks.get(1).getTitle())));
    }
}
