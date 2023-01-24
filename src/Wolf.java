package com.company;

/**
 * Wolves are social predators. They work together to bring down the preys.
 * The strategy of the wolf pack includes 2 phases
 *
 * - Phase one "STAY ALIVE": the wolves try to avoid collision with each other and with their prey.
 *                           They wait for the other animals to eliminate each other.
 *
 * - Phase two "THE HUNT IS ON": after waiting for a while, the number of other animals should be reduced, as the
 *                               wolves are now the majority, they actively chasing other animals.
 *
 *                               Each turn, the wolves always move in the same direction to avoid
 *                               self collision.
 */
public class Wolf implements Critter {
    private static final char EMPTY_SQUARE = '.';
    private static final String TACTICAL_PHASE_ONE = "STAY ALIVE";
    private static final String TACTICAL_PHASE_TWO = "THE HUNT IS ON";
    private static String tacticalPhase = TACTICAL_PHASE_ONE;
    private static int huntingMove = 0;
    private static int packMove = CENTER;
    private static int highestNumberOfMovesAmongPack = 0;
    private static int thisRoundTotalAdjacentPreys = 0;
    private static int numberOfRoundsWithLowNumberOfAdjacentPreys = 0;
    private static int numberOfRemainingWolves = 0;
    private static final RandomMoveGenerator randomMoveGenerator = new RandomMoveGenerator();
    private int numberOfMoves = 0;
    boolean isAlphaWolf = false; // this is the first survived wolf of this round

    @Override
    public char getChar() {
        return 'W';
    }

    @Override
    public int getMove(CritterInfo info) {
        numberOfMoves++;

        if (numberOfMoves > highestNumberOfMovesAmongPack) {
            isAlphaWolf = true;
            highestNumberOfMovesAmongPack = numberOfMoves;
            switchTacticIfNecessary();
            numberOfRemainingWolves = 1;
            thisRoundTotalAdjacentPreys = 0;
        } else {
            numberOfRemainingWolves++;
            isAlphaWolf = false;
        }

        if (tacticalPhase.equals(TACTICAL_PHASE_ONE)) {
            return stayAlive(info);
        } else {
            return hunt();
        }
    }

    private int stayAlive(CritterInfo info) {
        // getNeighborFromTheNorth
        char neighborFromTheNorth = info.getNeighbor(NORTH);
        char neighborFromTheSouth = info.getNeighbor(SOUTH);
        char neighborFromTheWest = info.getNeighbor(WEST);
        char neighborFromTheEast = info.getNeighbor(EAST);

        if (isAdjacentToAPrey(neighborFromTheNorth)) {
            thisRoundTotalAdjacentPreys++;

            if (neighborFromTheEast == EMPTY_SQUARE) {
                return EAST;
            } else if (neighborFromTheSouth == EMPTY_SQUARE) {
                return SOUTH;
            } else if (neighborFromTheWest == EMPTY_SQUARE) {
                return WEST;
            }
        }

        if (isAdjacentToAPrey(neighborFromTheEast)) {
            thisRoundTotalAdjacentPreys++;

            if (neighborFromTheSouth == EMPTY_SQUARE) {
                return SOUTH;
            } else if (neighborFromTheWest == EMPTY_SQUARE) {
                return WEST;
            } else if (neighborFromTheNorth == EMPTY_SQUARE) {
                return NORTH;
            }
        }

        if (isAdjacentToAPrey(neighborFromTheSouth)) {
            thisRoundTotalAdjacentPreys++;

            if (neighborFromTheWest == EMPTY_SQUARE) {
                return WEST;
            } else if (neighborFromTheNorth == EMPTY_SQUARE) {
                return NORTH;
            } else if (neighborFromTheEast == EMPTY_SQUARE) {
                return EAST;
            }
        }

        if (isAdjacentToAPrey(neighborFromTheWest)) {
            thisRoundTotalAdjacentPreys++;

            if (neighborFromTheNorth == EMPTY_SQUARE) {
                return NORTH;
            } else if (neighborFromTheEast == EMPTY_SQUARE) {
                return EAST;
            } else if (neighborFromTheSouth == EMPTY_SQUARE) {
                return SOUTH;
            }
        }

        return CENTER;
    }

    private boolean isAdjacentToAPrey(char animal) {
        return animal != getChar() && animal != EMPTY_SQUARE;
    }

    private void switchTacticIfNecessary() {
        // The heuristic here is that if the majority of wolf pack has not seen any prey in the adjacent area for a certain amount
        // of time, that could mean the number of prey has fallen drastically. The wolves are now the dominant species.
        // Instead of trying to avoid collision, they should actively hunt down the remaining preys.
        if (thisRoundTotalAdjacentPreys < numberOfRemainingWolves / 10) {
            numberOfRoundsWithLowNumberOfAdjacentPreys++;
        } else {
            numberOfRoundsWithLowNumberOfAdjacentPreys = 0;
        }

        if (numberOfRoundsWithLowNumberOfAdjacentPreys > 400) {
            tacticalPhase = TACTICAL_PHASE_TWO;
        }
    }

    private int hunt() {
        if (isAlphaWolf) {
            // The alpha wolf chooses a move for the wolf pack, this is to avoid accidental collision between members
            // Generally, the wolf pack wants to move southeast. This is to counter the movement of the mouse and the turtles.
            // Sometimes the wolf pack moves randomly to increase the chance of catching prey.
            if (huntingMove == 0) {
                packMove = randomMoveGenerator.getRandomMove();
            } else if (huntingMove % 2 != 0) {
                packMove = EAST;
            } else {
                packMove = SOUTH;
            }

            huntingMove++;
            if (huntingMove > 10) {
                huntingMove = 0;
            }
        }

        return packMove;
    }
}
