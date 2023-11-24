package de.workshops.bookshelf.domain;

import java.util.List;

public class BookTestData {

    public static List<Book> knownBooks() {
        return List.of(
                new Book("Clean Code", "A book about clean code", "Uncle Bob", "12345"),
                new Book("Spring Boot 3", "Build nice applications fast", "Someone", "54321"),
                new Book("IntelliJ Productivity", "Program code very fast", "Also Someone", "98765")
        );
    }
}
