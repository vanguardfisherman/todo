package com.todo;

import com.cecar.ToDoList;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ToDoList todo = new ToDoList();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== To-Do List =====");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver tareas");
            System.out.println("3. Marcar tarea como hecha");
            System.out.println("4. Eliminar tarea");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Descripción de la tarea: ");
                    String desc = sc.nextLine();
                    try {
                        todo.addTask(desc);
                        System.out.println("✅ Tarea agregada");
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ No se puede agregar una tarea vacía.");
                    }
                }
                case 2 -> {
                    if (todo.size() == 0) {
                        System.out.println("⚠️ No hay tareas registradas.");
                    } else {
                        for (int i = 0; i < todo.size(); i++) {
                            System.out.println(i + ". " + todo.getTask(i));
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Número de tarea a marcar: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    if (index >= 0 && index < todo.size()) {
                        todo.markTaskAsDone(index);
                        System.out.println("✅ Tarea marcada como hecha.");
                    } else {
                        System.out.println("⚠️ Índice inválido.");
                    }
                }
                case 4 -> {
                    System.out.print("Número de tarea a eliminar: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    if (index >= 0 && index < todo.size()) {
                        todo.getTasks().remove(index);
                        System.out.println("🗑️ Tarea eliminada.");
                    } else {
                        System.out.println("⚠️ Índice inválido.");
                    }
                }
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("⚠️ Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
