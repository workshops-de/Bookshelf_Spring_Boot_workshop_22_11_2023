package de.workshops.bookshelf.persistence;

import de.workshops.bookshelf.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends ListCrudRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByAuthorContains(String author);

    @Query("select b from Book b where (b.isbn = :isbn or :isbn is null) and (b.author like '%' || :author || '%' or :author is null)")
    List<Book> findBooksByIsbnAndAuthor(@Param("isbn") String isbn, @Param("author") String author);
}
