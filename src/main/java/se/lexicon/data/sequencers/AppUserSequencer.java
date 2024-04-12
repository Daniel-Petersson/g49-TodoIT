package se.lexicon.data.sequencers;

public class AppUserSequencer {
    private static int currentId = 0;

    public static int nextId() {
        return currentId++;
    }
}
