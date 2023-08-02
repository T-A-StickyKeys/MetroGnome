package com.ourgdx.game.LevelSystem.Mapping;

public enum TileType {

    floor('f'),
    indent('i'),
    hole('h'),
    wall('w'),
    background('b');

    public final char value;
    private TileType(char symbol) {
        value  = symbol;
    }

    public static TileType getTileType(char symbol){
        for (TileType t : TileType.values()){
            if (symbol == t.value){
                return t;
            }
        }
        return background; // TODO: Background being the else/default could be dangerous.
    }

}
