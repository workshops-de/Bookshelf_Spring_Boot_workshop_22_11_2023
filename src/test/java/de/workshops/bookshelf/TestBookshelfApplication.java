package de.workshops.bookshelf;

import org.springframework.boot.SpringApplication;

public class TestBookshelfApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(BookshelfApplication::main)
                .with(PostgresContainerTestConfiguration.class).run(args);
    }
}
