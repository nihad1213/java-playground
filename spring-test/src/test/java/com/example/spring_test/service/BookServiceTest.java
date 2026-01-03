package com.example.spring_test.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.spring_test.model.Book;
import com.example.spring_test.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldReturnAllBooks() {
        when(bookRepository.findAll())
                .thenReturn(List.of(
                        new Book(1L, "Clean Code"),
                        new Book(2L, "Spring Boot")
                ));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        assertEquals("Clean Code", books.get(0).getTitle());
    }
}