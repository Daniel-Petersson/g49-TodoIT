package se.lexicon.data.sequencers;

import se.lexicon.util.EntityType;

import java.util.EnumMap;

public class IdSequencer {
    private static IdSequencer instance = null;
    private EnumMap<EntityType,Integer> idMap;

    private IdSequencer(){
        idMap = new EnumMap<>(EntityType.class);
        for (EntityType type:EntityType.values()){
            idMap.put(type,0);
        }
    }

    public static IdSequencer getInstance(){
        if (instance==null){
            instance= new IdSequencer();
        }
        return instance;
    }
    public int nextId(EntityType type) {
        int currentId = idMap.get(type);
        idMap.put(type, ++currentId);
        return currentId;
    }

    public void setCurrentId(EntityType type, int id) {
        idMap.put(type, id);
    }

    public int getCurrentId(EntityType type) {
        return idMap.get(type);
    }
}
