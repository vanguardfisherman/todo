package com.cecar;

import com.library.Book;
import com.library.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;

    @BeforeEach
    void setup() {
        File f = new File("library.json");
        if (f.exists()) {
            f.delete();
        }
        library = new Library();
    }

    @Test
    void testAddBook() {
        Book book = new Book(1, "1984", "George Orwell");
        library.addBook(book);
        assertNotNull(library.findBookById(1));
    }

    @Test
    void testBorrowBook() {
        Book book = new Book(2, "El Principito", "Antoine de Saint-Exupéry");
        library.addBook(book);
        library.borrowBook(2);
        assertFalse(library.findBookById(2).isAvailable());
    }

    @Test
    void testReturnBook() {
        Book book = new Book(3, "Don Quijote", "Cervantes");
        library.addBook(book);
        library.borrowBook(3);
        library.returnBook(3);
        assertTrue(library.findBookById(3).isAvailable());
    }
}
