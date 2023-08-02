package com.ourgdx.game.LevelSystem.Mapping;

public class Tile {
    public char[] value;

    public char getType(){
        return value[0];
    }
    public char getHeight(){
        return value[1];
    }
    public char getVariation(){
        return value[2];
    }
}
