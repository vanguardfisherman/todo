package com.library;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== Biblioteca =====");
            System.out.println("1. Agregar libro");
            System.out.println("2. Ver libros");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Título: ");
                    String title = sc.nextLine();
                    System.out.print("Autor: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    System.out.println("✅ Libro agregado");
                }
                case 2 -> {
                    if (library.getBooks().isEmpty()) {
                        System.out.println("⚠️ No hay libros registrados.");
                    } else {
                        library.getBooks().forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID del libro a prestar: ");
                    int id = sc.nextInt();
                    library.borrowBook(id);
                    System.out.println("📕 Libro prestado (si existía y estaba disponible).");
                }
                case 4 -> {
                    System.out.print("ID del libro a devolver: ");
                    int id = sc.nextInt();
                    library.returnBook(id);
                    System.out.println("📖 Libro devuelto (si existía).");
                }
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("⚠️ Opción inválida.");
            }
        } while (option != 0);

        sc.close();
    }
}
