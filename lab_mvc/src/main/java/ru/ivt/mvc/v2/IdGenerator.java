package ru.ivt.mvc.v2;

public class IdGenerator {
    // поле хранит ID для каждой новой Task
    private static int nextId = 0;

    public static int getNextId() {
        return ++nextId;
    }
}
