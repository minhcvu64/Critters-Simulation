package com.company;

public class Frog implements Critter {
    // Declare numOfMoves to calculate how many steps a Frog has moved as the current direction
    // and then change direction by directionOfMove.
    private int numOfMoves = 3;
    private int directionOfMove;
    private static final RandomMoveGenerator randomMoveGenerator = new RandomMoveGenerator();

    @Override
    public char getChar() {
        return 'F';
    }

    @Override
    public int getMove(CritterInfo info) {
        // Value of numOfMoves will increase after each step.
        numOfMoves++;
        // When value of numOfMoves has reached 3 then reset to 1 and change the direction.
        if (numOfMoves > 3) {
            numOfMoves = 1;
            directionOfMove = randomMoveGenerator.getRandomMove();
        }

        return directionOfMove;
    }
}
