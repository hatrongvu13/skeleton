package com.jax.controllers;

import com.google.protobuf.Descriptors;
import com.jax.services.BookAuthorClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookAuthorClientService bookAuthorClientService;

    @GetMapping("/author/{id}")
    public Map<Descriptors.FieldDescriptor, Object> getAuthorBook(@PathVariable("id") String authorId) {
        return bookAuthorClientService.getAuthor(Integer.valueOf(authorId));
    }
}
