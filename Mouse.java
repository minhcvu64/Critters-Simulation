package com.company;

public class Mouse implements Critter {
    // The first move direction is always westward.
    private boolean moveWest = true;

    @Override
    public char getChar() {
        return 'M';
    }

    @Override
    public int getMove(CritterInfo info) {
        // In order of a mouse to move in a zig zag to the NorthWest
        // get a boolean type to flip the direction of a mouse after each move.
        if (moveWest) {
            moveWest = false;
            return WEST;
        } else {
            moveWest = true;
            return NORTH;
        }
    }
}
