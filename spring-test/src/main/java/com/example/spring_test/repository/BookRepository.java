package com.example.spring_test.repository;

import java.util.List;

import com.example.spring_test.model.Book;

public interface BookRepository {

    List<Book> findAll();
}