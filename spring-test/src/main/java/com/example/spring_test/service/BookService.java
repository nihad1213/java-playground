package com.example.spring_test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_test.model.Book;
import com.example.spring_test.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}