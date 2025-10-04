package com.notes;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        NoteDAO dao = new NoteDAO();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== Notas =====");
            System.out.println("1. Agregar nota");
            System.out.println("2. Ver notas");
            System.out.println("3. Editar nota");
            System.out.println("4. Eliminar nota");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Título: ");
                    String title = sc.nextLine();
                    System.out.print("Contenido: ");
                    String content = sc.nextLine();
                    dao.addNote(title, content);
                    System.out.println("✅ Nota agregada.");
                }
                case 2 -> {
                    var notes = dao.getAllNotes();
                    if (notes.isEmpty()) {
                        System.out.println("⚠️ No hay notas.");
                    } else {
                        notes.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID de la nota a editar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo título: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Nuevo contenido: ");
                    String newContent = sc.nextLine();
                    dao.updateNote(id, newTitle, newContent);
                    System.out.println("✏️ Nota actualizada.");
                }
                case 4 -> {
                    System.out.print("ID de la nota a eliminar: ");
                    int id = sc.nextInt();
                    dao.deleteNote(id);
                    System.out.println("🗑️ Nota eliminada.");
                }
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("⚠️ Opción inválida.");
            }
        } while (option != 0);

        sc.close();
    }
}
