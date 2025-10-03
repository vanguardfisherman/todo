package com.cecar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    @Test
    void testAddTask() {
        ToDoList todo = new ToDoList();
        todo.addTask("Estudiar Java");
        assertEquals(1, todo.size());
        assertEquals("Estudiar Java", todo.getTask(0).getDescription());
    }

    @Test
    void testMarkTaskAsDone() {
        ToDoList todo = new ToDoList();
        todo.addTask("Hacer ejercicio");
        todo.markTaskAsDone(0);
        assertTrue(todo.getTask(0).isDone());
    }

    @Test
    void testMultipleTasks() {
        ToDoList todo = new ToDoList();
        todo.addTask("Comprar comida");
        todo.addTask("Llamar a mamá");
        assertEquals(2, todo.size());
    }

    @Test
    void testEmptyTaskThrowsException() {
        ToDoList todo = new ToDoList();
        assertThrows(IllegalArgumentException.class, () -> todo.addTask(""));
    }
}
