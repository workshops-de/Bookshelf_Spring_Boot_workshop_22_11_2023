package de.workshops.bookshelf.config;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookshelfUserRepository extends ListCrudRepository<BookshelfUser, Long>, UserDetailsService {

    Optional<BookshelfUser> findByUsername(String username);

    @Override
    default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
