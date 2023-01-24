package com.company;

public class Turtle implements Critter {
    // Declare numOfMoves start at value 0
    private int numOfMoves = 0;

    @Override
    public char getChar() {
        return 'T';
    }

    @Override
    public int getMove(CritterInfo info) {
        //    16 17 18 19 20
        //     _ _ _ _ _ _
        // 15 |           | 1
        // 14 |           | 2
        // 13 |           | 3
        // 12 |           | 4
        // 11 | _ _ _ _ _ | 5
        //     10 9 8 7 6

        // Value of numOfMoves will increase after each step
        numOfMoves++;
        //  When value of numOfMoves has reached 20, the turtle finishes a cycle.
        //  numOfMoves is then reset to 1 to start a new one.
        if (numOfMoves > 20) {
            numOfMoves = 1;
        }
        // The turtle changes direction every 5 moves clockwise.
        if (numOfMoves <= 5) {
            return SOUTH;
        } else if (numOfMoves<= 10) {
            return WEST;
        } else if (numOfMoves <= 15) {
            return NORTH;
        } else {
            return EAST;
        }
    }
}
