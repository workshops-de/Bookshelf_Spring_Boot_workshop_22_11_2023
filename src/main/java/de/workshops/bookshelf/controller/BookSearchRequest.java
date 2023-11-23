package de.workshops.bookshelf.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookSearchRequest {

    @NotBlank
    @Size(min = 3)
    private String author;

    private String isbn;
}
