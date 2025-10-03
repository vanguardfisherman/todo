package com.cecar;

public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La tarea no puede estar vacía");
        }
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (done ? "[x] " : "[ ] ") + description;
    }
}
