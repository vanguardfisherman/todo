package com.library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private static final String FILE_PATH = "library.json";
    private Gson gson = new Gson();

    public Library() {
        this.books = loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBookById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void borrowBook(int id) {
        Book book = findBookById(id);
        if (book != null) {
            book.borrow();
            saveBooks();
        }
    }

    public void returnBook(int id) {
        Book book = findBookById(id);
        if (book != null) {
            book.returnBook();
            saveBooks();
        }
    }

    private void saveBooks() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Book> loadBooks() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Book>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
