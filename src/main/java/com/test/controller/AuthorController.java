package com.test.controller;

import com.test.model.Author;
import com.test.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @GetMapping
    public Page<Author> getAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        return authorService.getAllAuthors(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }
}
