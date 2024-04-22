package se.lexicon.data.sequencers.decaprecated;

public class PersonIdSequencer {
    private static int currentId = 0;

    public static int nextId() {
        return ++currentId;
    }

}
