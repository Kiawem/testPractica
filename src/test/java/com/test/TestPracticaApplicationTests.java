package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dto.BookDto;
import com.test.model.Author;
import com.test.model.Book;
import com.test.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestPracticaApplicationTests {

    @Autowired
    private MockMvc mock;

    @MockitoBean
    private BookService bookService;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAddBook() throws Exception {
        BookDto dto = new BookDto("Преступление и наказание", 1866, "Роман", "Фёдор Достоевский");
        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle(dto.getTitle());
        savedBook.setGenre(dto.getGenre());
        savedBook.setYear(dto.getYear());
        savedBook.setAuthor(new Author(2L, dto.getAuthorName(), 1821));
        System.out.println(objectMapper.writeValueAsString(savedBook));


        when(bookService.addBook(any(BookDto.class))).thenReturn(savedBook);

        mock.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Преступление и наказание"))
                .andExpect(jsonPath("$.genre").value("Роман"));
    }

    @Test
    void contextLoads() {
    }

}
