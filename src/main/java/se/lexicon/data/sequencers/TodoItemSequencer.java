package se.lexicon.data.sequencers;

public class TodoItemSequencer {
    private static int currentId = 1000;

    public static int nextId() {
        return currentId++;
    }

    public static void setCurrentId(int id) {
        currentId = id;
    }

    public static int getCurrentId() {
        return currentId;
    }
}
