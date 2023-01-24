package com.company;

public interface Critter {
    public char getChar();

    public int getMove(CritterInfo info);



    public static final int NORTH = -1;

    public static final int SOUTH = 3;

    public static final int EAST = 8;

    public static final int WEST = 11;

    public static final int CENTER = 42;
}

