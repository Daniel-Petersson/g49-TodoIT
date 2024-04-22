package se.lexicon.data.sequencers.decaprecated;

public class TodoItemSequencer {
    private static int currentId = 0;

    public static int nextId() {
        return ++currentId;
    }

    public static void setCurrentId(int id) {
        currentId = id;
    }

    public static int getCurrentId() {
        return currentId;
    }
}
