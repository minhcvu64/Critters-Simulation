package com.company;

public class Bird implements Critter {
    private static final RandomMoveGenerator randomMoveGenerator = new RandomMoveGenerator();

    @Override
    public char getChar() {
        return 'B';
    }

    @Override
    public int getMove(CritterInfo info) {
        return randomMoveGenerator.getRandomMove();
    }
}
