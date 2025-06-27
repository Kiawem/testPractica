package com.test.service;

import com.test.dto.BookDto;
import com.test.model.Author;
import com.test.model.Book;
import com.test.repo.AuthorRepository;
import com.test.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book addBook(BookDto bookDto) {
        Author author = authorRepository.findByName(bookDto.getAuthorName())
                .orElseThrow(() -> new RuntimeException("Автора нет в базе"));
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setYear(bookDto.getYear());
        book.setGenre(bookDto.getGenre());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book updateBook(Long id, BookDto dto) {
        Book book = getBookById(id);
        Author author = authorRepository.findByName(dto.getAuthorName())
                .orElseThrow(() -> new RuntimeException("Автор не найден: " + dto.getAuthorName()));

        book.setTitle(dto.getTitle());
        book.setGenre(dto.getGenre());
        book.setYear(dto.getYear());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
